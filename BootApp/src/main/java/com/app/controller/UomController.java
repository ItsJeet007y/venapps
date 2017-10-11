package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Uom;
import com.app.service.IUomService;
import com.app.util.UomUtil;

@Controller
public class UomController {
	@Autowired
	private IUomService service;
	@Autowired
	private UomUtil uomUtil;
	
	@GetMapping(value= {"/regPage","/"})
	public String showRegPage() {
		return "RegisterPage";
	}
	
	@PostMapping("/regUom")
	public String regUom(@ModelAttribute Uom uom,ModelMap map) {
		long uomId=service.saveUom(	uom);
		map.addAttribute("message","Uom "+uomId+" created");
		return "RegisterPage";
	}
	
	@GetMapping("/showAllUom")
	public String showUom(ModelMap map) {
		List<Uom> uomList=service.getAll();
		map.addAttribute("uoms",uomList);
		return "ShowData";
	}
	
	@GetMapping("/deleteUom")
	public String deletUom(@RequestParam("uomId")long uomId) {// @RequestParam= request.getParameter("uomId");
		service.deleteById(uomId);
		return "redirect:showAllUom";
	}
	
	@GetMapping("/editUom")
	public String showEdit(@RequestParam("uomId")long uomId,ModelMap map) {
		Uom ob=service.getOneById(uomId);
		map.addAttribute("uom",ob);
		Map<String,String> mapList=uomUtil.getUomTypes();
		map.addAttribute("uomTypes",mapList);
		return "UomDataEdit";
	}
	@PostMapping("/updateUom")
	public String updateUom(@ModelAttribute Uom uom) {
		service.updateUom(uom);
		return "redirect:showAllUom";
	}
}
