package com.cykj.base.common.em.ext;

import com.cykj.base.common.em.EnumSuper;

public enum LogEnum implements EnumSuper{
	//活动
	ActAttend("活动","活动参与对象"),ActGift("活动","奖品卡券管理"),ActGiftMaster("活动","抽奖模板管理"),ActGiftSlave("活动","抽奖模板奖品管理"),ActHistory("活动","活动报名"),
	ActMaster("活动","活动管理"),ActRecommendConfig("活动","推荐有礼设置"),ActRecommendDetail("活动","推荐有礼报名管理"),ActType("活动","活动类型"),
	//贷款
	LoanConfig("贷款",""),LoanHistory("贷款",""),
	//日志
	LogLogin("日志",""),LogOperater("日志",""),LogSchedule("日志",""),LogSystem("日志",""),
	//养车
	MtInsurance("养车","保险服务"),MtOilCard("养车","加油卡充值"),MtPeccancy("养车","违章代办"),MtPeccancyDetail("养车","违章代办明细"),MtPeccancySubscribe("养车","违章代办订阅"),MtRescue("养车","救援服务"),
	//预约
	ScdSchedule("预约","预约管理"),ScdTime("预约","预约时间设置"),ScdType("预约","预约类型"),
	//商城
	ShopCar("商城",""),ShopCarBrand("商城",""),ShopCarClass("商城",""),ShopCarImg("商城",""),ShopCarOld("商城",""),
	ShopCarOldBuy("商城",""),ShopCarOldImg("商城",""),ShopCarParts("商城",""),ShopCarPartsImg("商城",""),
	ShopCarTag("商城",""),ShopCarTagTemp("商城",""),ShopOrgBrandKey("商城",""),ShopPartsClass("商城",""),
	ShopPartsClassSlave("商城",""),ShopPartsOrder("商城",""),
	//系统
	SysCardLevelConfig("",""),SysDepartment("",""),SysMenu("",""),SysOrganization("",""),SysOrgScore("",""),SysRole("",""),SysRoleMenu("",""),
	SysScoreType("",""),SysServerFee("",""),SysTradeConfig("",""),SysTradeSlave("",""),SysUser("",""),SysUserOld("",""),
	//用户
	UsrConsume("用户",""),UsrRecharge("用户",""),UsrScoreDetail("用户",""),UsrUser("用户",""),UsrUserCar("用户",""),UsrUserSlave("用户","")
	;
	private String value;
	private String discription;
	
	LogEnum(String value, String discription) {
		this.value = value;
		this.discription = discription;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getDiscription() {
		return discription;
	}

	
}
