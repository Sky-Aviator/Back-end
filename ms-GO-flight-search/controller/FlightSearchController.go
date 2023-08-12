package controller

import (
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/streadway/amqp"

	"ms-GO-flight-search/model/services"
)

type FlightSearchController struct {
	FSService     *services.FlightSearchService
	RabbitChannel *amqp.Channel
}

func NewFlightSearchController(fsService *services.FlightSearchService, rabbitChannel *amqp.Channel) *FlightSearchController {
	return &FlightSearchController{
		FSService:     fsService,
		RabbitChannel: rabbitChannel,
	}
}

func (ctrl *FlightSearchController) StatusService(c *gin.Context) {
	port := c.GetString("port")
	c.String(http.StatusOK, fmt.Sprintf("Service running at port: %s", port))
}
func (ctrl *FlightSearchController) SearchFlightsOneWay(c *gin.Context) {
	cidadeOrigem := c.Query("from")
	cidadeDestino := c.Query("to")
	partidaPrevista := c.Query("going")

	flightsOneWay, err := ctrl.FSService.SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	if len(flightsOneWay) == 0 {
		c.Status(http.StatusNoContent)
		return
	}

	// Convert the data to a proper JSON structure
	var jsonData []map[string]interface{}
	for _, flight := range flightsOneWay {
		jsonData = append(jsonData, flight)
	}

	c.JSON(http.StatusOK, jsonData)
}

// func (ctrl *FlightSearchController) SearchFlightsOneWay(c *gin.Context) {
// 	cidadeOrigem := c.Query("from")
// 	cidadeDestino := c.Query("to")
// 	partidaPrevista := c.Query("going")

// 	fmt.Printf("From: %s, To: %s, Going: %s\n", cidadeOrigem, cidadeDestino, partidaPrevista)

// 	flightsOneWay, err := ctrl.FSService.SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista)
// 	if err != nil {
// 		fmt.Println("Error:", err)
// 		c.AbortWithStatus(http.StatusInternalServerError)
// 		return
// 	}

// 	if len(flightsOneWay) == 0 {
// 		c.Status(http.StatusNoContent)
// 		return
// 	}

// 	c.JSON(http.StatusOK, flightsOneWay)
// }

func (ctrl *FlightSearchController) SearchFlightsRoundtrip(c *gin.Context) {
	cidadeOrigem := c.Query("from")
	cidadeDestino := c.Query("to")
	partidaPrevista := c.Query("going")
	partidaPrevista2 := c.Query("return")

	flightsOneWay, err := ctrl.FSService.SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	flightsReturn, err := ctrl.FSService.SearchFlights(cidadeDestino, cidadeOrigem, partidaPrevista2)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	if len(flightsOneWay) == 0 || len(flightsReturn) == 0 {
		c.Status(http.StatusNoContent)
		return
	}

	result := services.NewFlightSearchResult(flightsOneWay, flightsReturn)
	c.JSON(http.StatusOK, result)
}

func (ctrl *FlightSearchController) SendingBookingTicketGoing(c *gin.Context) {
	cidadeOrigem := c.Query("from")
	cidadeDestino := c.Query("to")
	partidaPrevista := c.Query("going")

	flightsOneWay, err := ctrl.FSService.SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	// Create an empty slice for the return flights since we're only dealing with one-way bookings here
	var flightsReturn []map[string]interface{}

	result := services.NewFlightSearchResult(flightsOneWay, flightsReturn)

	jsonData, err := json.Marshal(result)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	err = ctrl.RabbitChannel.Publish(
		"",              // exchange
		"booking_queue", // routing key
		false,           // mandatory
		false,           // immediate
		amqp.Publishing{
			ContentType: "application/json",
			Body:        jsonData,
		},
	)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	c.JSON(http.StatusOK, result)
}

func (ctrl *FlightSearchController) SendingBookingTicketRoundtrip(c *gin.Context) {
	cidadeOrigem := c.Query("from")
	cidadeDestino := c.Query("to")
	partidaPrevista := c.Query("going")
	partidaPrevista2 := c.Query("return")

	flightsOneWay, err := ctrl.FSService.SearchFlights(cidadeOrigem, cidadeDestino, partidaPrevista)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	flightsReturn, err := ctrl.FSService.SearchFlights(cidadeDestino, cidadeOrigem, partidaPrevista2)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	result := services.NewFlightSearchResult(flightsOneWay, flightsReturn)

	jsonData, err := json.Marshal(result)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	err = ctrl.RabbitChannel.Publish(
		"",              // exchange
		"booking_queue", // routing key
		false,           // mandatory
		false,           // immediate
		amqp.Publishing{
			ContentType: "application/json",
			Body:        jsonData,
		},
	)
	if err != nil {
		c.AbortWithStatus(http.StatusInternalServerError)
		return
	}

	c.JSON(http.StatusOK, result)
}
