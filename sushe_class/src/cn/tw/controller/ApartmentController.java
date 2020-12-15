package cn.tw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tw.domain.Apartment;
import cn.tw.pagination.Page;
import cn.tw.service.ApartmentService;
import cn.tw.util.UtilFuns;

@Controller
public class ApartmentController {

	@Resource
	ApartmentService apartmentService;


	@RequestMapping("/apm/getdata.action")
	public String getdata(String sex,Model model) throws Exception{
		if(UtilFuns.isNotEmpty(sex)) {
			if(sex.equals("b")) sex="鐢�";
			else sex="濂�";	
		}
		else sex=null;
		Map paraMap= new HashMap();
		paraMap.put("sex", sex);
		List<Apartment> list =apartmentService.find(paraMap);
		
 		JSONArray jsonArray=new JSONArray();
 		jsonArray.addAll(list);
 		String data=jsonArray.toString();
 		System.out.println(data);
 		model.addAttribute("data",data);
 		
		return "/cla/getdata.jsp";
	}

	@RequestMapping("/apm/gettotalfloor.action")
	public String gettotalfloor(String apartmentId,Model model) throws Exception{
		String floor=apartmentService.get(apartmentId).getTotalFloor();
		JSONObject json=new JSONObject();
		json.put("status", "200");
		json.put("floor", floor);
		String data=json.toString();
		model.addAttribute("data",data);
		return "/apm/getdata.jsp";
	}
	
	
	@RequestMapping("/apm/list.action")
	public String apmlist(String sex,String key,String totalPage,Integer pageNo,Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();
		if(sex!=null){
			if(sex.equals("b"))	map.put("sex", "鐢�"); else if(sex.equals("g")) map.put("sex", "濂�"); else map.put("sex", null); 
		}else map.put("sex", null);
		if(key!=null) key=URLDecoder.decode(key, "UTF-8");
		map.put("key","%"+key+"%");

		model.addAttribute("sex",sex);
		model.addAttribute("key",key);
		Page page= new Page();
		page.setParams(map);
		if(pageNo==null) pageNo=1;
		else if(pageNo<1) pageNo=1;
		
		page.setPageNo(pageNo);
		page.setPageSize(5);
		if(pageNo==1) {
			getTotalPage(page);
			if(  page.getTotalRecord()*1.0/page.getPageSize()-
				 page.getTotalRecord()/page.getPageSize() <0.000001 )
				page.setTotalPage(page.getTotalRecord()/page.getPageSize());
			else {
					page.setTotalPage(page.getTotalRecord()/page.getPageSize()+1);
			}
		}
		else page.setTotalPage(Integer.valueOf(totalPage));
		model.addAttribute("page",page);
		
		List<Apartment> dataList=apartmentService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/apm/list.jsp";
	}
  public void getTotalPage(Page page){
		String s=apartmentService.findResultSize(page);
		if(s==null) page.setTotalRecord(0); 
		else page.setTotalRecord(Integer.valueOf(apartmentService.findResultSize(page)));
	}

	@RequestMapping("/apm/tocreate.action")
	public String tocreate(Model model) {
		return "/apm/create.jsp";
	}

	@RequestMapping("/apm/create.action")
	public String create(Apartment apm, Model model) {
		apm.setApartmentId(UUID.randomUUID().toString().substring(0, 8));
		apartmentService.insert(apm);
		return "redirect:/apm/list.action";
	}
	@RequestMapping("/apm/toupdate.action")
	public String toupdate(String apmId, Model model) {
		Apartment apm = apartmentService.get(apmId);
		model.addAttribute("obj", apm);
		return "/apm/update.jsp";
	}
	@RequestMapping("/apm/update.action")
	public String update(Apartment apm, Model model) {
		apartmentService.update(apm);
		return "redirect:/apm/list.action";
	}
	
	@RequestMapping("/apm/deletebyid.action")
	public String deletebyid(String pageNo,String totalPage,String apmId, Model model) 
			throws NumberFormatException, UnsupportedEncodingException {
		apartmentService.deleteById(apmId);
		return apmlist(null,null,totalPage, Integer.valueOf(pageNo), model);
	}
	
	@RequestMapping("/apm/delete.action")
	public String delete(String pageNo,String totalPage,String sb, String msg,Model model) throws NumberFormatException, UnsupportedEncodingException {
		String[] ids=sb.split(",");
		apartmentService.delete(ids);
		return apmlist(null,null,totalPage,Integer.valueOf(pageNo), model);
	}
	/*	




*/
}
