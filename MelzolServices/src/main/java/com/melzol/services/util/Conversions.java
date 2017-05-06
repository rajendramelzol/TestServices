package com.melzol.services.util;

import java.util.HashMap;
import java.util.Map;

public class Conversions {
	
	public static  Map<Integer, String> activityEventType=new HashMap<Integer,String>();
	public static  Map<Integer, String> activityEventSubType=new HashMap<Integer,String>();
	public static  Map<Integer, String> activityCategory=new HashMap<Integer,String>();
	public static  Map<Integer, String> eventCategory=new HashMap<Integer,String>();
	public static  Map<Integer, String> gossipsCategory=new HashMap<Integer,String>();
	public static  Map<Integer, String> wrkRefType=new HashMap<Integer,String>();
	public static  Map<Integer, String> notificationType=new HashMap<Integer,String>();
	public static  Map<Integer, String> notificationStatus=new HashMap<Integer,String>();
	public static  Map<Integer, String> commentRefType=new HashMap<Integer,String>();
	
	static{
		
	//activityEventType
		activityEventType.put(1, "City");
		activityEventType.put(2, "Neighbourhood");
	
	//activityEventSubType
	activityEventSubType.put(1, "Activity");
	activityEventSubType.put(2, "Event");
	
	//activityCategory
	activityCategory.put(1, "Shopping");
	activityCategory.put(2, "Wellness");
	activityCategory.put(3, "Day Outing");
	activityCategory.put(4, "Nightlife & Pubs");
	activityCategory.put(5, "Food");
	activityCategory.put(6, "Kids Activity");
	activityCategory.put(7, "Bowling");
	activityCategory.put(8, "Go Karting");
	activityCategory.put(9, "Sports & Fitness");
	activityCategory.put(10, "Rentals");
	activityCategory.put(11, "Workshops");
	activityCategory.put(12, "Photography");
	
	//eventCategory
	eventCategory.put(1, "Techie");
	eventCategory.put(2, "Social & Corporate");
	eventCategory.put(3, "Education");
	eventCategory.put(4, "Music & Dance");
	eventCategory.put(5, "Hobbies & Crafts");
	eventCategory.put(6, "Films");
	eventCategory.put(7, "Fashion & Beauty");
	eventCategory.put(8, "Adventures & Outdoors");
	eventCategory.put(9, "Health & Wellness");
	eventCategory.put(10, "Politics");
	eventCategory.put(11, "Pets");
	eventCategory.put(12, "Sports & Fitness");
	
	//gossipsCategory
	gossipsCategory.put(1, "Re-views");
	gossipsCategory.put(2, "Q's & A's");
	gossipsCategory.put(3, "Hu-Ru-mours");
	gossipsCategory.put(4, "Tech-Savvy");
	gossipsCategory.put(5, "Film Buff");
	gossipsCategory.put(6, "Sports Fantastic");
	gossipsCategory.put(7, "Hot News");
	gossipsCategory.put(8, "Wellness");
	gossipsCategory.put(9, "Ideas Hub");
	gossipsCategory.put(10, "Food Cooking");
	gossipsCategory.put(11, "Your Experience");
	gossipsCategory.put(12, "Travel");
	gossipsCategory.put(13, "Politics");
	gossipsCategory.put(14, "Fashion & Beauty");
	gossipsCategory.put(15, "Relationship");
	gossipsCategory.put(16, "LifeStyle");
	gossipsCategory.put(17, "Gadgets");
	gossipsCategory.put(18, "Education & Jobs");
	gossipsCategory.put(19, "Workplace");
	gossipsCategory.put(20, "Women");
	gossipsCategory.put(21, "Men");
	gossipsCategory.put(22, "Kids");
	gossipsCategory.put(23, "Pets");
	gossipsCategory.put(24, "Others");
	
	//wrkRefType
	wrkRefType.put(1, "Group");
	wrkRefType.put(2, "Event");
	wrkRefType.put(3, "Gossips");
	wrkRefType.put(4, "Blogs");
	wrkRefType.put(5, "Forum");
	wrkRefType.put(6, "Services");
	wrkRefType.put(7, "members");
	
	//wrkRefType
	commentRefType.put(1, "Group");
	commentRefType.put(2, "Event");
	commentRefType.put(3, "Gossips");
	commentRefType.put(4, "Blogs");
	commentRefType.put(5, "Forum");
	commentRefType.put(6, "Services");
	commentRefType.put(7, "topic");
	
	//notificationType
	notificationType.put(1, "Comment");
	notificationType.put(2, "Like");
	notificationType.put(3, "Tagging");
	
	//notificationStatus
	notificationStatus.put(1,"Read");
	notificationStatus.put(2, "UnRead");
	}
	
	
	public static Map<Integer, String> getActivityEventType() {
		return activityEventType;
	}
	public static void setActivityEventType(Map<Integer, String> activityEventType) {
		Conversions.activityEventType = activityEventType;
	}
	public static Map<Integer, String> getActivityEventSubType() {
		return activityEventSubType;
	}
	public static void setActivityEventSubType(Map<Integer, String> activityEventSubType) {
		Conversions.activityEventSubType = activityEventSubType;
	}
	
	public static Map<Integer, String> getActivityCategory() {
		return activityCategory;
	}
	public static void setActivityCategory(Map<Integer, String> activityCategory) {
		Conversions.activityCategory = activityCategory;
	}
	public static Map<Integer, String> getEventCategory() {
		return eventCategory;
	}
	public static void setEventCategory(Map<Integer, String> eventCategory) {
		Conversions.eventCategory = eventCategory;
	}
	public static Map<Integer, String> getGossipsCategory() {
		return gossipsCategory;
	}
	public static void setGossipsCategory(Map<Integer, String> gossipsCategory) {
		Conversions.gossipsCategory = gossipsCategory;
	}
	
	
	

}
