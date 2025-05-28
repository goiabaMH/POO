package br.ifs.tdd.interfaces;

import br.ifs.tdd.service.ServicoRelatorio;

public interface INotificacao {
	public boolean enviarNotificacao(ServicoRelatorio relatorio);
	
}
