package com.hcl.management.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.management.dto.PortfolioDetailsResponseDto;
import com.hcl.management.dto.PurchaseRequestDto;
import com.hcl.management.dto.PurchaseResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.feignclient.PortfolioConnect;
import com.hcl.management.model.Account;
import com.hcl.management.model.Purchase;
import com.hcl.management.repository.PurchaseRepository;
import com.hcl.management.service.IAccountService;
import com.hcl.management.service.IPurchaseService;
import com.hcl.management.util.DateTimeUtil;
import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.exception.PortfolioNotFoundException;
import com.hcl.portfolio.model.Portfolio;

@Service
public class PurchaseServiceImpl implements IPurchaseService {
	
	@Autowired
	PortfolioConnect portfolioConnect;
	
	@Autowired
	IAccountService iAccountService;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	public String purchase(PurchaseRequestDto purchaseRequestDto) throws ManagementException, PortfolioNotFoundException {	
		Account account=new Account();
		BeanUtils.copyProperties(iAccountService.findByAccountId(purchaseRequestDto.getAccountId()), account);
		
		PortfolioResponseDto portfolioResponseDto=portfolioConnect.findById(purchaseRequestDto.getPortfolioId());
		
		Purchase purchase=new Purchase();
		purchase.setName(portfolioResponseDto.getName());
		purchase.setQuantity(purchaseRequestDto.getQuantity());
		purchase.setPrice(portfolioResponseDto.getPrice());
		purchase.setDateTime(DateTimeUtil.dateTime());
		purchase.setAccount(account);
		
		purchaseRepository.save(purchase);
		
		return "Purchased Successfully";
	}
	
	public List<PurchaseResponseDto> getByAccountId(int accountId) throws ManagementException{
		List<Purchase> purchaseList=purchaseRepository.findPurchaseByAccountAccountId(accountId);
		if(purchaseList.isEmpty()) {
			throw new ManagementException("No purchases for this account");
		}
		return IPurchaseService.convertPurchaseListToPurchaseResponseDtoList(purchaseList);
		
	}
	
	public PortfolioDetailsResponseDto getById(int accountId) throws ManagementException, PortfolioNotFoundException{
		Purchase purchase=purchaseRepository.findPurchaseById(accountId);
		if(purchase==null) {
			throw new ManagementException("Id doesn't exists");
		}
		PortfolioDetailsResponseDto portfolioDetailsResponseDto=new PortfolioDetailsResponseDto();
		portfolioDetailsResponseDto.setPurchaseResponseDto(IPurchaseService.convertPurchaseToPurchaseResponseDto(purchase));
		portfolioDetailsResponseDto.setCurrentPrice(portfolioConnect.findByName(purchase.getName()).getBody().getPrice());
		return portfolioDetailsResponseDto;
				
		
	}

}
