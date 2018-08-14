package br.com.catalogo.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.catalogo.model.Item;
import br.com.catalogo.service.ItemService;


@CrossOrigin(origins = "*")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/catalogo/itens", method = RequestMethod.POST)
	public ResponseEntity<Item> cadastrarItem(@RequestBody Item item){
		
		Item itemCadastrado = itemService.cadastrar(item);
		return new ResponseEntity<> (itemCadastrado,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/catalogo/itens", method = RequestMethod.GET)
	public ResponseEntity<Collection<Item>> getitem() {
		Collection<Item> itens = itemService.buscarTodos();
		return new ResponseEntity<>(itens, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogo/itens/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Item> setDisciplina(@PathVariable("codigo") String codigo, @RequestBody Item item) throws Exception {
		Item itemAlterado = itemService.update(item, codigo);
		return new ResponseEntity<>(itemAlterado,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogo/itens/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Item> buscaId(@PathVariable("codigo") String codigo) throws Exception {
		return new ResponseEntity<>(itemService.BuscaId(codigo), HttpStatus.OK);
	}
	@RequestMapping(value = "/catalogo/itens/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Item> deleteDisciplina(@PathVariable("codigo") String codigo) throws Exception {
		return new ResponseEntity<>(itemService.excluir(codigo), HttpStatus.OK);
	}
}
