package br.ifs.tdd.service;

import java.util.Map;

import br.ifs.tdd.interfaces.IConfiguracaoDesconto;
import br.ifs.tdd.interfaces.IEstrategiaDesconto;

public class ServicoConfiguracaoDesconto implements IConfiguracaoDesconto{
	private Map<String,IEstrategiaDesconto> estrategias;
	public IEstrategiaDesconto getEstrategia(String desconto) {
		return null;
	}

}
