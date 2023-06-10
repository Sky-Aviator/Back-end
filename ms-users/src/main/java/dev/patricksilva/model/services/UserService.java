package dev.patricksilva.model.services;

public interface UserService {
	public String maskCPF(String cpf);
	public String maskCard(String card);
	public String maskCv(String cardCv);
}
