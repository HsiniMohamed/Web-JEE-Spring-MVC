package org.sid.web;

import org.sid.dao.IProduitRespository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
public class ProduitController {
	
	@Autowired
	private IProduitRespository iProduitRespository;
	
	@GetMapping(path="/")
	public String index() {
		
		return "redirect:/index";
	}
	
	@GetMapping(path="/index")
	public String products(Model model,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		Page<Produit> pageProduits = iProduitRespository.findByDesignationContains(motCle,PageRequest.of(page, size));
		model.addAttribute("pageProduits", pageProduits);
		model.addAttribute("currentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("motCle",motCle);
		model.addAttribute("pages",new int[pageProduits.getTotalPages()]);
		return "products";
	}
	
	@GetMapping(path = "/deleteProduit")
	public String delete(Long id,String motCle,int page, int size) {
		iProduitRespository.deleteById(id);
		return "redirect:/index?page="+page+"&motCle="+motCle+"&size="+size;
	}
	
	@GetMapping(path="/form")
	public String formProduit(Model model) {	
		model.addAttribute("produit",new Produit());
		return "formProduit";
	}
	
	@PostMapping(path="/save")
	public String save(@Valid Produit produit, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) return "formProduit";
		iProduitRespository.save(produit);
		model.addAttribute("produit",produit);
		return "confirmation";
	}
	
	@GetMapping(path="/edit")
	public String edit(Model model, long id) {	
		Produit produit = iProduitRespository.findById(id).get();
		model.addAttribute("produit",produit);
		return "editProduit";
	}
	

}
