package util;

import android.text.TextUtils;
import android.util.Log;
import model.City;
import model.CoolWeatherDB;
import model.County;
import model.Province;

public class Utility {

	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String Response){
		
		if(!TextUtils.isEmpty(Response)){
			String[] allProvinces=Response.split(",");
			if(allProvinces!=null && allProvinces.length>0){
				for(String p:allProvinces){
					String[] array=p.split("\\|");
					Province province=new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String Response,int provinceId){
		if(!TextUtils.isEmpty(Response)){
			String[] allcities=Response.split(",");
			if(allcities!=null && allcities.length>0){
				for(String c:allcities){
					String[] array=c.split("\\|");
					City city=new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String Response,int cityId){
		if(!TextUtils.isEmpty(Response)){
			String[] allcounties=Response.split(",");
			if(allcounties!=null && allcounties.length>0){
				for(String c:allcounties){
					String[] array=c.split("\\|");
					County county=new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
