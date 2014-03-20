package com.excilys.rgueirard.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.domain.PageWrapper;

public class Pagination extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageWrapper<Computer> wrapper;
		
	@Override
	public int doStartTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		
		String url = "dashboard?page=";
		String params = "&nbDisplay="+wrapper.getNbDisplay()+"&orderBy="+wrapper.getOrderBy()+"&ascendant="+wrapper.isAscendant()+"&searchType="+wrapper.getSearchType()+"&searchMotif="+wrapper.getSearchMotif(); 
		
		try {
			out.print("<table><tr>");
						
			if(wrapper.getCurrentPage() != 1){
				out.print("<td><a href=\""+url+(wrapper.getCurrentPage()-1)+params+"\" class=\"btn primary\">Prev</a></td>");
			}
			if(wrapper.getNbPages() > 10){
				for(int i=1;i<4;i++){
					if(wrapper.getCurrentPage() == i){
						out.print("<td>"+i+"</td>");
					} else {
						out.print("<td><a href=\""+url+i+params+"\">"+i+"</a></td>");
					}
				}
				if(wrapper.getCurrentPage()<4){
					out.print("<td>...</td>");				
				}
				if(wrapper.getCurrentPage()==4){
					out.print("<td>4</td><td>...</td>");
				}
				if(wrapper.getCurrentPage() == 5){
					out.print("<td><a href=\""+url+4+params+"\">"+4+"</a></td>");
					out.print("<td>5</td><td>...</td>");
				}
				if((wrapper.getCurrentPage()>5)&&(wrapper.getCurrentPage() < wrapper.getNbPages()-4)){
					out.print("<td>...</td>");
					for(int i=wrapper.getCurrentPage()-1;i<wrapper.getCurrentPage()+2;i++){
						if(wrapper.getCurrentPage() == i){
							out.print("<td>"+i+"</td>");
						} else {
							out.print("<td><a href=\""+url+i+params+"\">"+i+"</a></td>");
						}
					}
					out.print("<td>...</td>");
				}
				if(wrapper.getCurrentPage() == wrapper.getNbPages()-4){
					out.print("<td>...</td><td>"+(wrapper.getNbPages()-4)+"</td>");
					out.print("<td><a href=\""+url+(wrapper.getNbPages()-3)+params+"\">"+(wrapper.getNbPages()-3)+"</a></td>");
				}
				if(wrapper.getCurrentPage() == wrapper.getNbPages()-3){
					out.print("<td>...</td><td>"+(wrapper.getNbPages()-3)+"</td>");
				}
				if(wrapper.getCurrentPage() > wrapper.getNbPages()-3){
					out.print("<td>...</td>");
				}
				for(int i=wrapper.getNbPages()-2;i<wrapper.getNbPages()+1;i++){
					if(wrapper.getCurrentPage()==i){
						out.print("<td>"+i+"</td>");
					} else {
						out.print("<td><a href=\""+url+i+params+"\">"+i+"</a></td>");
					}
				}
			} else {
				for(int i=1;i < wrapper.getNbPages()+1;i++){
					if(wrapper.getCurrentPage() == i){
						out.print("<td>"+i+"</td>");
					} else {
						out.print("<td><a href=\""+url+i+params+"\">"+i+"</a></td>");
					}
				}
			}
			if(wrapper.getCurrentPage() < wrapper.getNbPages()){
				out.print("<td><a href=\""+url+(wrapper.getCurrentPage()+1)+params+"\" class=\"btn primary\">Next</a></td>");
			}
			
			out.print("</tr></table>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
	
	
	
	
	
	
	
}
