package com.zam.o2o.dto;

import java.util.List;

import com.zam.o2o.entity.LocalAuth;
import com.zam.o2o.enums.LocalAuthStateEnum;

public class LocalAuthExecution {
	// 结果状态
	private int state;
	// 状态标示
	private String stateInfo;
	private int count;

	private LocalAuth localAuth;
	private List<LocalAuth> LocalAuthList;

	public LocalAuthExecution() {

	}

	// 店铺操作失败的时候使用的构造器
	public LocalAuthExecution(LocalAuthStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 店铺操作成功时使用的构造器
	public LocalAuthExecution(LocalAuthStateEnum stateEnum, LocalAuth localAuth) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.localAuth = localAuth;
	}

	// 查询店铺成功的时候使用的构造器
	public LocalAuthExecution(LocalAuthStateEnum stateEnum, List<LocalAuth> localAuthList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.LocalAuthList = localAuthList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

    public LocalAuth getLocalAuth() {
        return localAuth;
    }

    public void setLocalAuth(LocalAuth localAuth) {
        this.localAuth = localAuth;
    }

    public List<LocalAuth> getLocalAuthList() {
        return LocalAuthList;
    }

    public void setLocalAuthList(List<LocalAuth> localAuthList) {
        LocalAuthList = localAuthList;
    }

	

}
