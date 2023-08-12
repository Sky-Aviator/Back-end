package main

import (
	"database/sql"
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	_ "github.com/go-sql-driver/mysql" // Import the MySQL driver
	"github.com/streadway/amqp"

	// "dev.patricksilva/controller"
	"ms-GO-flight-search/controller"

	"ms-GO-flight-search/model/repositories"
	// "dev.patricksilva/model/repositories"

	"ms-GO-flight-search/model/services"
	// "dev.patricksilva/model/services"
)

func main() {
	gin.SetMode(gin.ReleaseMode)

	dbURL := "localhost:3306"
	dbUser := "root"
	dbPassword := "PLdS6482@123456789"
	dbName := "flight-search"

	dbConnStr := fmt.Sprintf("%s:%s@tcp(%s)/%s", dbUser, dbPassword, dbURL, dbName) // Updated connection string
	db, err := sql.Open("mysql", dbConnStr)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Set up RabbitMQ connection
	rabbitConn, err := amqp.Dial("amqp://guest:guest@localhost:5672/")
	if err != nil {
		log.Fatal(err)
	}
	defer rabbitConn.Close()

	rabbitChannel, err := rabbitConn.Channel()
	if err != nil {
		log.Fatal(err)
	}
	defer rabbitChannel.Close()

	// Initialize repositories
	flightSearchRepository := repositories.NewFlightSearchRepositoryImpl(db)

	// Initialize services
	flightSearchService := services.NewFlightSearchService(flightSearchRepository)

	// Initialize controllers
	flightSearchController := controller.NewFlightSearchController(flightSearchService, rabbitChannel)

	// Set up the HTTP server using gin
	router := gin.Default()
	router.Use(func(c *gin.Context) {
		c.Set("port", os.Getenv("PORT"))
		c.Next()
	})

	router.GET("/api/v1/flight-search/status", flightSearchController.StatusService)
	router.GET("/api/v1/flight-search/flights/oneway", flightSearchController.SearchFlightsOneWay)
	router.GET("/api/v1/flight-search/flights/roundtrip", flightSearchController.SearchFlightsRoundtrip)
	router.POST("/api/v1/flight-search/flights/booking-going", flightSearchController.SendingBookingTicketGoing)
	router.POST("/api/v1/flight-search/flights/booking-roundtrip", flightSearchController.SendingBookingTicketRoundtrip)

	port := os.Getenv("PORT")
	if port == "" {
		port = "9095"
	}

	serverAddr := fmt.Sprintf(":%s", port)
	log.Printf("Starting server on %s...\n", serverAddr)
	log.Fatal(http.ListenAndServe(serverAddr, router))
}
