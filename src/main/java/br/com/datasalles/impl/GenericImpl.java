package br.com.datasalles.impl;

import java.util.List;

import br.com.datasalles.domain.ItemVenda;
import br.com.datasalles.domain.Venda;

public interface GenericImpl {
	
	void salvar(Venda venda, List<ItemVenda> itens);
	void salvarBoleto(Venda venda, List<ItemVenda> itens);

}
