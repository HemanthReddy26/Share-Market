package com.hcl.portfolio.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.exception.PortfolioNotFoundException;
import com.hcl.portfolio.model.Portfolio;

@Service
public interface PortfolioService {
	
	public Portfolio updatePortfolio(int portfolioId, PortfolioResponseDto portfoliodto);
	
	public PortfolioResponseDto findById(int portfolioId) throws PortfolioNotFoundException;
	
	public PortfolioResponseDto findByName(String portfolioName) throws PortfolioNotFoundException;
	
	public static PortfolioResponseDto convertPortfolioToPortfolioResponseDto(Portfolio portfolio) {
		PortfolioResponseDto portfolioResponseDto=new PortfolioResponseDto();
		BeanUtils.copyProperties(portfolio,portfolioResponseDto);
		return portfolioResponseDto;
	}

}
