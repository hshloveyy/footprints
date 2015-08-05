package com.mvc.footprints.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mvc.footprints.entity.TCategory;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TKind;
import com.mvc.footprints.entity.TPageCity;
import com.mvc.footprints.entity.TPageProvince;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.entity.TSubCategory;
import com.mvc.footprints.entity.TSubKind;
import com.mvc.footprints.entity.TYellowPageInfo;
import com.mvc.footprints.service.ICategoryService;
import com.mvc.footprints.service.ICityService;
import com.mvc.footprints.service.IKindService;
import com.mvc.footprints.service.IPageCityService;
import com.mvc.footprints.service.IPageProvinceService;
import com.mvc.footprints.service.IProvinceService;
import com.mvc.footprints.service.ISubCategoryService;
import com.mvc.footprints.service.ISubKindService;
import com.mvc.footprints.service.IYellowPageInfoService;

public class SelectTag extends TagSupport {

	private static final String SELECT_TYPE_PROVINCE = "province";
	private static final String SELECT_TYPE_CITY = "city";
	private static final String SELECT_TYPE_PAGE_PROVINCE = "pageprovince";
	private static final String SELECT_TYPE_PAGE_CITY = "pagecity";
	private static final String SELECT_TYPE_CATEGORY = "category";
	private static final String SELECT_TYPE_SUBCATEGORY = "subcategory";
	private static final String SELECT_TYPE_KIND = "kind";
	private static final String SELECT_TYPE_SUBKIND = "subkind";
	private static final String SELECT_TYPE_PAGE = "page";
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4740347878982213027L;
	
	private String id;
	private String type;
	private String name;
	private String value;
	private String parentId;
	private String onchange;

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		try {
            JspWriter out = this.pageContext.getOut();
            out.print("<select name='" + name + "' id='" + id + "' " + (StringUtils.isNotBlank(onchange)?"onchange=" + onchange:"") + ">");
        	out.print("<option value='0'>请选择</option>");
        	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext());
            if(SELECT_TYPE_PROVINCE.equals(type)){
            	IProvinceService provinceService = (IProvinceService)wac.getBean("provinceService");
            	List<TProvince> list = (List<TProvince>) provinceService.findAll(TProvince.class);
            	
            	for (TProvince tProvince : list) {
            		out.print("<option value='" + tProvince.getId() + "' " + (((tProvince.getId()+"").equals(value))?"checked='checked'":"") + ">" + tProvince.getName() + "</option>");
				}
            }else if(SELECT_TYPE_CITY.equals(type)){
            	ICityService cityService = (ICityService)wac.getBean("cityService");
            	List<TCity> list = (List<TCity>) cityService.findAll(TCity.class);
            	
            	for (TCity city : list) {
            		out.print("<option value='" + city.getId() + "' " + (((city.getId()+"").equals(value))?"checked='checked'":"") + ">" + city.getCityName() + "</option>");
				}
            }else if(SELECT_TYPE_CATEGORY.equals(type)){
            	ICategoryService categoryService = (ICategoryService)wac.getBean("categoryService");
            	List<TCategory> list = (List<TCategory>) categoryService.findAll(TCategory.class);
            	
            	for (TCategory category : list) {
            		out.print("<option value='" + category.getId() + "' " + (((category.getId()+"").equals(value))?"checked='checked'":"") + ">" + category.getCategoryName() + "</option>");
				}
            }else if(SELECT_TYPE_SUBCATEGORY.equals(type)){
            	ISubCategoryService subCategoryService = (ISubCategoryService)wac.getBean("subCategoryService");
            	List<TSubCategory> list = (List<TSubCategory>) subCategoryService.findAll(TSubCategory.class);
            	
            	for (TSubCategory subCategory : list) {
            		out.print("<option value='" + subCategory.getId() + "' " + (((subCategory.getId()+"").equals(value))?"checked='checked'":"") + ">" + subCategory.getSubCategoryName() + "</option>");
				}
            }else if(SELECT_TYPE_KIND.equals(type)){
            	IKindService kindService = (IKindService)wac.getBean("kindService");
            	List<TKind> list = (List<TKind>) kindService.findAll(TKind.class);
            	
            	for (TKind kind : list) {
            		out.print("<option value='" + kind.getId() + "' " + (((kind.getId()+"").equals(value))?"checked='checked'":"") + ">" + kind.getKindName() + "</option>");
				}
            }else if(SELECT_TYPE_SUBKIND.equals(type)){
            	ISubKindService subKindService = (ISubKindService)wac.getBean("subKindService");
            	List<TSubKind> list = (List<TSubKind>) subKindService.findAll(TSubKind.class);
            	
            	for (TSubKind subKind : list) {
            		out.print("<option value='" + subKind.getId() + "' " + (((subKind.getId()+"").equals(value))?"checked='checked'":"") + ">" + subKind.getSubKindName() + "</option>");
				}
            }else if(SELECT_TYPE_PAGE_PROVINCE.equals(type)){
            	IPageProvinceService pageProvinceService = (IPageProvinceService)wac.getBean("pageProvinceService");
            	List<TPageProvince> list = (List<TPageProvince>) pageProvinceService.findAll(TPageProvince.class);
            	
            	for (TPageProvince tProvince : list) {
            		out.print("<option value='" + tProvince.getId() + "' " + (((tProvince.getId()+"").equals(value))?"checked='checked'":"") + ">" + tProvince.getName() + "</option>");
				}
            }else if(SELECT_TYPE_PAGE_CITY.equals(type)){
            	IPageCityService pageCityService = (IPageCityService)wac.getBean("pageCityService");
            	List<TPageCity> list = (List<TPageCity>) pageCityService.findAll(TPageCity.class);
            	
            	for (TPageCity city : list) {
            		out.print("<option value='" + city.getId() + "' " + (((city.getId()+"").equals(value))?"checked='checked'":"") + ">" + city.getCityName() + "</option>");
				}
            }else if(SELECT_TYPE_PAGE.equals(type)){
            	IYellowPageInfoService yellowPageInfoService = (IYellowPageInfoService)wac.getBean("yellowPageInfoService");
            	List<TYellowPageInfo> list = (List<TYellowPageInfo>) yellowPageInfoService.findAll(TYellowPageInfo.class);
            	
            	for (TYellowPageInfo page : list) {
            		out.print("<option value='" + page.getId() + "' " + (((page.getId()+"").equals(value))?"checked='checked'":"") + ">" + page.getName() + "</option>");
				}
            }
            out.print("</select>");
        } catch(Exception e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	
}
