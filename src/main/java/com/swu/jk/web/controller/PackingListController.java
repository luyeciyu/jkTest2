package com.swu.jk.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.Export;
import com.swu.jk.domain.PackingList;
import com.swu.jk.service.PackingListService;
import com.swu.jk.util.UtilFuns;
import com.swu.jk.vo.PackingListVO;

@Controller
@RequestMapping("/cargo/packinglist")
public class PackingListController {
	
	@Resource
	private PackingListService packingListService;
	
	
	@RequestMapping("/list.action")
	public String list(Model model){
		
		List<PackingList> dataList = packingListService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/packinglist/jPackingListList.jsp";
	}
	
	@RequestMapping("/tocreate.action")
	public String toCreate(String id, Model model){
		String[] ids = UtilFuns.splitStr(id, ",");
		
		List<Map> data = packingListService.getExportToPackingListData(ids);
		model.addAttribute("dataList", data);
		return "/cargo/packinglist/jPackingListCreate.jsp";
	}
	
	@RequestMapping("/insert.action")
	public String insert(PackingList packingList, Model model, HttpServletRequest request){
		String[] array = request.getParameterValues("exportIds");
		
		String exportIDs = "";
		String exportNos = "";
		for(int i = 0; i < array.length; i++){
			String[] param = array[i].split("\\|");
			exportIDs += param[0] + "|";
			exportNos += param[1] + "|";
		}
		exportIDs = UtilFuns.delLastChar(exportIDs);
		exportNos = UtilFuns.delLastChar(exportNos);
		
		packingList.setExportIds(exportIDs);
		packingList.setExportNos(exportNos);
		packingListService.insert(packingList);
		return "redirect:/cargo/packinglist/list.action";
	}
	
	@RequestMapping("/toview.action")
	public String view(String id, Model model){
		PackingList packingList = packingListService.get(id);
		model.addAttribute("o", packingList);
		
		String exportIds = packingList.getExportIds();
		String exportNos = packingList.getExportNos();
		
		String[] _exportIds = exportIds.split("\\|");
		String[] _exportNos = exportNos.split("\\|");
		
		List<Map> data = new ArrayList<Map>();
		Map<String, Object> map = null;
		Export export = null;
		for(int i = 0; i < _exportIds.length; i++){
			map = new HashMap<String, Object>();
			map.put("exportId", _exportIds[i]);
			map.put("exportNo", _exportNos[i]);
			data.add(map);
		}
		model.addAttribute("dataList", data);
		
		return "/cargo/packinglist/jPackingListView.jsp";
	}
	
	@RequestMapping("/delete.action")
	public String delete(String id, Model model){
		String[] ids = UtilFuns.splitStr(id, ",");
		packingListService.delete(ids);
		return "redirect:/cargo/packinglist/list.action";
	}
	
	@RequestMapping("/toupdate.action")
	public String toUpdate(String id, Model model){
		PackingList packingList = packingListService.get(id);
		model.addAttribute("o", packingList);
		
		String exportIds = packingList.getExportIds();
		String exportNos = packingList.getExportNos();
		
		String[] _exportIds = exportIds.split("\\|");
		String[] _exportNos = exportNos.split("\\|");
		
		List<Map> data = new ArrayList<Map>();
		Map<String, Object> map = null;
		Export export = null;
		for(int i = 0; i < _exportIds.length; i++){
			map = new HashMap<String, Object>();
			map.put("exportId", _exportIds[i]);
			map.put("exportNo", _exportNos[i]);
			data.add(map);
		}
		model.addAttribute("dataList", data);
		
		return "/cargo/packinglist/jPackingListUpdate.jsp";
	}
	
	
	@RequestMapping("/update.action")
	public String update(PackingList packingList, Model model, HttpServletRequest request){
		
		String[] array = request.getParameterValues("exportIds");
		
		String exportIDs = "";
		String exportNos = "";
		for(int i = 0; i < array.length; i++){
			String[] param = array[i].split("\\|");
			exportIDs += param[0] + "|";
			exportNos += param[1] + "|";
		}
		exportIDs = UtilFuns.delLastChar(exportIDs);
		exportNos = UtilFuns.delLastChar(exportNos);
		
		packingList.setExportIds(exportIDs);
		packingList.setExportNos(exportNos);
		
		packingListService.update(packingList);
		return "redirect:/cargo/packinglist/list.action";
	}
}
