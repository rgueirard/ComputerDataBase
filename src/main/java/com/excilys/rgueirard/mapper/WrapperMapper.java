package com.excilys.rgueirard.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.dto.ComputerDTO;
import com.excilys.rgueirard.wrapper.PageWrapper;

@Component
public class WrapperMapper {
	
	@Autowired
	private ComputerMapper computerMapper;
	
	
	public PageWrapper<ComputerDTO> computerToDTO(PageWrapper<Computer> wrapper) {
		PageWrapper<ComputerDTO> wrapperDTO = new PageWrapper<ComputerDTO>();
		
		if (wrapper.getCurrentPage() != 1) {
			wrapperDTO.setCurrentPage(wrapper.getCurrentPage());
		}
		if (wrapper.getNbDisplay() != 50) {
			wrapperDTO.setNbDisplay(wrapper.getNbDisplay());
		}
		if (wrapper.getNbPages() != 0) {
			wrapperDTO.setNbPages(wrapper.getNbPages());
		}
		if (wrapper.getOrderBy() != 1) {
			wrapperDTO.setOrderBy(wrapper.getOrderBy());
		}
		if (wrapper.getSearchMotif() != "") {
			wrapperDTO.setSearchMotif(wrapper.getSearchMotif());
		}
		if (wrapper.getSearchType() != 0) {
			wrapperDTO.setSearchType(wrapper.getSearchType());
		}
		if (wrapper.getSize() != 0) {
			wrapperDTO.setSize(wrapper.getSize());
		}
		
		wrapperDTO.setAscendant(wrapper.isAscendant());
		
		for(Computer computer : wrapper.getPages()){
			wrapperDTO.getPages().add(computerMapper.computerToDTO(computer));
		}

		return wrapperDTO;
	}

	public PageWrapper<Computer> DTOToComputer(PageWrapper<ComputerDTO> wrapperDTO) {
		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();
		
		if (wrapperDTO.getCurrentPage() != 1) {
			wrapper.setCurrentPage(wrapperDTO.getCurrentPage());
		}
		if (wrapperDTO.getNbDisplay() != 50) {
			wrapper.setNbDisplay(wrapperDTO.getNbDisplay());
		}
		if (wrapperDTO.getNbPages() != 0) {
			wrapper.setNbPages(wrapperDTO.getNbPages());
		}
		if (wrapperDTO.getOrderBy() != 1) {
			wrapper.setOrderBy(wrapperDTO.getOrderBy());
		}
		if (wrapperDTO.getSearchMotif() != "") {
			wrapper.setSearchMotif(wrapperDTO.getSearchMotif());
		}
		if (wrapperDTO.getSearchType() != 0) {
			wrapper.setSearchType(wrapperDTO.getSearchType());
		}
		if (wrapperDTO.getSize() != 0) {
			wrapper.setSize(wrapperDTO.getSize());
		}
		
		wrapper.setAscendant(wrapperDTO.isAscendant());
		
		for(ComputerDTO computerDTO : wrapperDTO.getPages()) {
			wrapper.getPages().add(computerMapper.DTOToComputer(computerDTO));
		}
		
		return wrapper;
	}
}
