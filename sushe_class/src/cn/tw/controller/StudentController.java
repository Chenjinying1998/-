package cn.tw.controller;

import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tw.domain.Bedroom;
import cn.tw.domain.College;
import cn.tw.domain.Student;
import cn.tw.pagination.Page;
import cn.tw.service.ApartmentService;
import cn.tw.service.BedroomService;
import cn.tw.service.ClassesService;
import cn.tw.service.CollegeService;
import cn.tw.service.StudentService;
import cn.tw.util.UtilFuns;

@Controller
public class StudentController {

	@Resource
	StudentService studentService;
	@Resource
	CollegeService collegeService;
	@Resource
	ClassesService classService;
	@Resource
	ApartmentService apartmentService;

	@Resource
	BedroomService bedroomService;

	@RequestMapping("/stu/getdata2.action")
	public String getdata2(Model model,String collegeId){
		Map map = new HashMap();
		if(UtilFuns.isEmpty(collegeId)) collegeId=null;
		map.put("collegeId", collegeId);
		List<Student> list=studentService.find(map);
		Integer allNum=0,bNum=0,gNum=0,bYNum=0,bNNum=0,gYNum=0,gNNum=0;
		for(Student student:list){
			allNum++;
			if(student.getSex().equals("鐢�")) { 
				bNum++;
				if(student.getBedroomId()==null) bNNum++;
				else bYNum++;
			}else{
				gNum++;
				if(student.getBedroomId()==null) gNNum++;
				else gYNum++;
			}
		}
		String tips1="  璇ュ闄㈠叡鏈夊鐢熶汉鏁帮細"+allNum+" 鍏朵腑鐢风敓"+bNum+"浜猴紝濂崇敓"+gNum+"浜�<br/>  鏈垎閰嶅鑸嶇殑鐢风敓"+bNNum
				+"  鏈垎閰嶅鑸嶇殑濂崇敓"+gNNum+"浜�";
		
		JSONObject json= new JSONObject();
		json.put("tips1", tips1);
		
//		list= new ArrayList<Student>();
//		Student student= new Student();
//		student.setStudentName("");
//		list.add(student);
// 		JSONArray jsonArray=new JSONArray();
// 		jsonArray.addAll(list);
// 		String data=jsonArray.toString();
 		model.addAttribute("data",json.toString());
		return "/cla/getdata.jsp";
	}
	
	
	@RequestMapping("/stu/getdata.action")
	public String getdata(Model model,String cla){
		Map map = new HashMap();
		if(UtilFuns.isEmpty(cla)) cla=null;
		map.put("classId", cla);
		List<Student> list=studentService.find(map);
 		JSONArray jsonArray=new JSONArray();
 		jsonArray.addAll(list);
 		String data=jsonArray.toString();
 		model.addAttribute("data",data);
		return "/cla/getdata.jsp";
	}	
	
	@RequestMapping("/stu/getdata1.action")
	public String getdata1(Model model,String stuId){
		if(UtilFuns.isEmpty(stuId)) stuId=null;
		List<Student> list=new ArrayList<Student>();
		list.add(	studentService.get(stuId));
 		JSONArray jsonArray=new JSONArray();
 		jsonArray.addAll(list);
 		String data=jsonArray.toString();
 		model.addAttribute("data",data);
		return "/cla/getdata.jsp";
	}
	
	
	@RequestMapping("/stu/list.action")
	public String annolist(String status, String college, String grade,
			String sex, String arg, String key, String totalPage,
			Integer pageNo, Model model) throws UnsupportedEncodingException {
		Map map = new HashMap();
		if (UtilFuns.isEmpty(arg))
			map.put("arg", null);
		else
			map.put("arg", arg);
		if (UtilFuns.isNotEmpty(key))
			key = URLDecoder.decode(key, "UTF-8");
		else
			key = null;

		if (UtilFuns.isEmpty(status))
			status = null;
		if (UtilFuns.isEmpty(college))
			college = null;
		if (UtilFuns.isNotEmpty(grade))
			grade = URLDecoder.decode(grade, "UTF-8");
		else
			grade = null;
		if (UtilFuns.isNotEmpty(sex))
			sex = URLDecoder.decode(sex, "UTF-8");
		else
			sex = null;

		map.put("status", status);
		map.put("college", college);
		map.put("grade", grade);
		map.put("sex", sex);

		map.put("key", "%" + key + "%");

		model.addAttribute("arg", arg);
		model.addAttribute("key", key);

		model.addAttribute("status", status);
		model.addAttribute("college", college);
		model.addAttribute("grade", grade);
		model.addAttribute("sex", sex);

		Page page = new Page();
		page.setParams(map);
		if (pageNo == null)
			pageNo = 1;
		else if (pageNo < 1)
			pageNo = 1;

		page.setPageNo(pageNo);
		page.setPageSize(5);
		if (pageNo == 1) {
			getTotalPage(page);
			if (page.getTotalRecord() * 1.0 / page.getPageSize()
					- page.getTotalRecord() / page.getPageSize() < 0.000001)
				page.setTotalPage(page.getTotalRecord() / page.getPageSize());
			else {
				page.setTotalPage(page.getTotalRecord() / page.getPageSize()
						+ 1);
			}
		} else
			page.setTotalPage(Integer.valueOf(totalPage));
		model.addAttribute("page", page);

		List<Student> dataList = studentService.findPage(page);
		model.addAttribute("dataList", dataList);
		return "/stu/list.jsp";
	}

	public void getTotalPage(Page page) {
		String s = studentService.findResultSize(page);
		if (s == null)
			page.setTotalRecord(0);
		else
			page.setTotalRecord(Integer.valueOf(s));
	}

	@RequestMapping("/stu/tocreate.action")
	public String tocreate(Model model) {
		List<College> sList1 = collegeService.find(null);
		model.addAttribute("sList1", sList1);
		return "/stu/create.jsp";
	}

	@RequestMapping("/stu/findbybedroomid.action")
	public String findbybedroomid(String bedroomId,String totalPage,	Integer pageNo, Model model) {
		Map paraMap= new HashMap();
		paraMap.put("bedroomId",bedroomId);
		List<Student> dataList=studentService.find(paraMap);
		model.addAttribute("dataList",dataList);
		return "/stu/list.jsp";
	}
	
	@RequestMapping("/stu/findbyclassid.action")
	public String findbyclassid(String classId,Model model) {
		Map paraMap= new HashMap();
		paraMap.put("classId",classId);
		List<Student> dataList=studentService.find(paraMap);
		model.addAttribute("dataList",dataList);
		return "/stu/list.jsp";
	}

	
	@RequestMapping("/stu/create.action")
	public String create(Student stu, Model model) {
		stu.setStudentId(UUID.randomUUID().toString().substring(0, 8));
		if (stu.getBedroomId() != null)
			stu.setStatus("Y");
		else
			stu.setStatus("N");
		studentService.insert(stu);
		return "redirect:/stu/list.action";
	}

	@RequestMapping("/stu/update.action")
	public String update(Student stu,HttpSession httpSession, Model model) {
		if (stu.getBedroomId() != null)
			stu.setStatus("Y");
		studentService.update(stu);
		if(httpSession.getAttribute("type").equals("student")){
			model.addAttribute("info","淇敼鎴愬姛!");
			return "/stu/info.jsp";
		}else{
			return "redirect:/stu/list.action";
		}
	}

	@RequestMapping("/stu/toupdate.action")
	public String toupdate(String stuId, Model model) {
		List<College> sList1 = collegeService.find(null);
		model.addAttribute("sList1", sList1);
		Student student = studentService.get(stuId);
		model.addAttribute("obj", student);

		model.addAttribute("sList2", bedroomService.find(null));

		return "/stu/update.jsp";
	}

	@RequestMapping("/stu/deletebyid.action")
	public String deletebyid(String pageNo, String totalPage, String stuId,
			Model model) throws NumberFormatException,
			UnsupportedEncodingException {
		studentService.deleteById(stuId);
		return annolist(null, null, null, null, null, null, totalPage,
				Integer.valueOf(pageNo), model);
	}

	@RequestMapping("/stu/delete.action")
	public String delete(String pageNo, String totalPage, String sb,
			String msg, Model model) throws NumberFormatException,
			UnsupportedEncodingException {
		String[] ids = sb.split(",");
		studentService.delete(ids);
		return annolist(null, null, null, null, null, null, totalPage,
				Integer.valueOf(pageNo), model);
	}

}
