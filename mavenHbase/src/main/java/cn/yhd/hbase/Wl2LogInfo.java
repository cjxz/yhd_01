package cn.yhd.hbase;

import java.io.Serializable;

/**  
 * @author myx
 * @createTime 2015年4月10日 上午10:32:22  
 * 
 */
public class Wl2LogInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6830431083804506900L;
	
	private Integer adapterCost;
	private Long adapterEndTime;
	private Long adapterStartTime;
	private Long appCallTime;
	private Integer appToServerCost;
	private String clientAppVersion;
	private String clientIp;
	private String clientSystem;
	private String clientVersion;
	private Integer costAll;
	private String data;
	private String deviceCode;
	private String errorCode;
	private Integer errorType;
	private String from;
	private String iaddr;
	private String latitude;
	private String level;
	private String longitude;
	private String netType;
	private String paras;
	private Long serverEndTime;
	private String serverIp;
	private Long serverStartTime;
	private String traderName;
	private String unionKey;
	private String urlPathMethod;
	private Integer serverCost;
	private String userId;
	
	public Integer getAdapterCost() {
		return adapterCost;
	}
	public void setAdapterCost(Integer adapterCost) {
		this.adapterCost = adapterCost;
	}
	public Long getAdapterEndTime() {
		return adapterEndTime;
	}
	public void setAdapterEndTime(Long adapterEndTime) {
		this.adapterEndTime = adapterEndTime;
	}
	public Long getAdapterStartTime() {
		return adapterStartTime;
	}
	public void setAdapterStartTime(Long adapterStartTime) {
		this.adapterStartTime = adapterStartTime;
	}
	public Long getAppCallTime() {
		return appCallTime;
	}
	public void setAppCallTime(Long appCallTime) {
		this.appCallTime = appCallTime;
	}
	public Integer getAppToServerCost() {
		return appToServerCost;
	}
	public void setAppToServerCost(Integer appToServerCost) {
		this.appToServerCost = appToServerCost;
	}
	public String getClientAppVersion() {
		return clientAppVersion;
	}
	public void setClientAppVersion(String clientAppVersion) {
		this.clientAppVersion = clientAppVersion;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientSystem() {
		return clientSystem;
	}
	public void setClientSystem(String clientSystem) {
		this.clientSystem = clientSystem;
	}
	public String getClientVersion() {
		return clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	public Integer getCostAll() {
		return costAll;
	}
	public void setCostAll(Integer costAll) {
		this.costAll = costAll;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Integer getErrorType() {
		return errorType;
	}
	public void setErrorType(Integer errorType) {
		this.errorType = errorType;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getIaddr() {
		return iaddr;
	}
	public void setIaddr(String iaddr) {
		this.iaddr = iaddr;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public String getParas() {
		return paras;
	}
	public void setParas(String paras) {
		this.paras = paras;
	}
	public Long getServerEndTime() {
		return serverEndTime;
	}
	public void setServerEndTime(Long serverEndTime) {
		this.serverEndTime = serverEndTime;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public Long getServerStartTime() {
		return serverStartTime;
	}
	public void setServerStartTime(Long serverStartTime) {
		this.serverStartTime = serverStartTime;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getUnionKey() {
		return unionKey;
	}
	public void setUnionKey(String unionKey) {
		this.unionKey = unionKey;
	}
	public String getUrlPathMethod() {
		return urlPathMethod;
	}
	public void setUrlPathMethod(String urlPathMethod) {
		this.urlPathMethod = urlPathMethod;
	}
	public Integer getServerCost() {
		return serverCost;
	}
	public void setServerCost(Integer serverCost) {
		this.serverCost = serverCost;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
