package com.findsoft.SpringMVCDemo.entity;

public class BabySongInfo {
	public int id;//����id
	public String name;//��������
	public String author;//��������
	public String singer;//�質��
	public String country;//������������
	public String isForeign;//�Ƿ������������0-��1-��
	public String isRecommend;//�Ƿ����Ƽ�����
	public String fileUrl;//�������ļ�·��
	public String coverImgUrl;//��������ͼƬ·��
	public String desc;//�������
	public String type;//��������
	public String createDate;//����ʱ��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIsForeign() {
		return isForeign;
	}
	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getCoverImgUrl() {
		return coverImgUrl;
	}
	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
