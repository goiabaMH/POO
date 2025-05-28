package br.ifs.tdd.service;

import br.ifs.tdd.interfaces.INotificacao;

public class ServicoNotificacao implements INotificacao{
	public boolean enviarNotificacao(ServicoRelatorio relatorio) {
		return true;
	}
}
