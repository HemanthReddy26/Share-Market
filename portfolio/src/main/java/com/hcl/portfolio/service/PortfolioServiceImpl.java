package com.hcl.portfolio.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.exception.PortfolioNotFoundException;
import com.hcl.portfolio.model.Portfolio;
import com.hcl.portfolio.repository.PortfolioRepository;

@Service
public class PortfolioServiceImpl implements PortfolioService{
	
	@Autowired
	PortfolioRepository portfolioRepository;

	@Override
	public Portfolio updatePortfolio(int portfolioId, PortfolioResponseDto portfolioResponseDto) {
	
		Portfolio port = portfolioRepository.findById(portfolioId).get();
		port.setName(portfolioResponseDto.getName());
		port.setPrice(portfolioResponseDto.getPrice());
		
		portfolioRepository.save(port);
		return port;
	}
	
	@Scheduled(cron = "0/10 * * ? * *")
	public void updatePrice() {
		List<Portfolio> portfolioList=portfolioRepository.findAll();
		portfolioList.forEach(portfolio->{
			portfolio.setPrice(Math.random()*1000);
			portfolioRepository.flush();
		});
	}

	@Override
	public PortfolioResponseDto findById(int portfolioId) throws PortfolioNotFoundException {
		if(portfolioRepository.findById(portfolioId).isPresent()) {
			throw new PortfolioNotFoundException("No portfolios for this id");
		}
		return PortfolioService.convertPortfolioToPortfolioResponseDto(portfolioRepository.getById(portfolioId));
	}

	@Override
	public PortfolioResponseDto findByName(String portfolioName) throws PortfolioNotFoundException {
		Portfolio portfolio=portfolioRepository.findPortfolioByName(portfolioName);
		if(portfolio==null) {
			throw new PortfolioNotFoundException("No portfolios with this name");
		}
		return PortfolioService.convertPortfolioToPortfolioResponseDto(portfolio);
	}

}
