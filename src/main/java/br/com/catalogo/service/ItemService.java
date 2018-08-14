package br.com.catalogo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.catalogo.exception.RegisterNotFoundException;
import br.com.catalogo.model.Item;
import br.com.catalogo.repository.ItemRepository;



@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	

	public Item cadastrar(Item item){
		
		return itemRepository.save(item);
	}
	public List<Item> buscarTodos() {
		return itemRepository.findAll();
	}
	
	
	public Item BuscaId(String codigo) throws Exception {
		Item disciplina = itemRepository.findOne(codigo);
		
		if(disciplina == null) {
			throw new RegisterNotFoundException("Essa disciplina nao existe!");
		}
		
		return disciplina;
	}

	public Item excluir(String codigo) throws Exception{
		Item item = itemRepository.findOne(codigo);
		
		if(item == null) {
			throw new RegisterNotFoundException("Esse item nao existe!");
		}
		
		itemRepository.delete(item);
		
		return item;
	}
	
	public Item alterar(Item item) {
		return itemRepository.save(item);
	}
	
	public Item update(Item item, String codigo) throws Exception {
		Item itemASerApagado = itemRepository.findOne(codigo);
		
		if(item == null | itemASerApagado == null) {
			throw new RegisterNotFoundException("Esse item nao existe!");
		}
		
		itemRepository.delete(itemASerApagado);
		
		itemRepository.save(item);
		
		return item;
	}
}
