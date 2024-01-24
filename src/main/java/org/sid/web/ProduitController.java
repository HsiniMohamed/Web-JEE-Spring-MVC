package org.sid.web;

import org.sid.dao.IProduitRespository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProduitController {
	
	@Autowired
	private IProduitRespository iProduitRespository;
	
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

}
