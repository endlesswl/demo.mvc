package com.palm.lingcai.util;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 概率抽奖
 * 
 * @author LDL
 */
public class PrizeDrawUtil {
	
	/**
	 * 根据概率抽奖
	 * @param marketRule
	 * @return
	 */
	public static String draw(String marketRule) {
		JSONObject jsonOb = JSONObject.parseObject(marketRule);
		JSONArray jsonArray = jsonOb.getJSONArray("rule");
		Object[][] prizeArr1 = new Object[jsonArray.size()][4];
		// 概率数组
		Double probability[] = new Double[jsonArray.size()];
		
		for (int i = 0; i < prizeArr1.length; i++) {
			prizeArr1[i][0] = jsonArray.getJSONObject(i).getInteger("index");
			prizeArr1[i][1] = jsonArray.getJSONObject(i).getString("code");
			prizeArr1[i][2] = jsonArray.getJSONObject(i).getString("name");
			prizeArr1[i][3] = jsonArray.getJSONObject(i).getDouble("probability");
			probability[i]=(Double) prizeArr1[i][3];
		}

		int index = getRand(probability);

		String code = (String) prizeArr1[index][1];
		return code;
	}

	// 根据概率获取奖项
	private static Integer getRand(Double obj[]) {
		Integer result = null;
		try {
			int sum = 0;// 概率数组的总概率精度
			for (int i = 0; i < obj.length; i++) {
				sum += obj[i];
			}
			for (int i = 0; i < obj.length; i++) {// 概率数组循环
				int randomNum = new Random().nextInt(sum);// 随机生成1到sum的整数
				if (randomNum < obj[i]) {// 中奖
					result = i;
					break;
				} else {
					sum -= obj[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void main(String[] args) {
 		String jsonRule = "{\"rotateName\":\"零彩\",\"rule\":[{\"index\":0,\"code\":\"0.05\",\"name\":\"0.05\",\"probability\":50},{\"index\":1,\"code\":\"0.2\",\"name\":\"0.2\",\"probability\":25},{\"index\":2,\"code\":\"0.1\",\"name\":\"0.1\",\"probability\":25}]}";
 		
 		Map<String, Integer> map = new HashMap<String, Integer>();
		
 		double totalMoney=120000;
 		int totalCount=0;
		while(true){
			String name = draw(jsonRule);
			// 统计次数
			Integer c = map.get(name);
			
			double tt = totalMoney - Double.parseDouble(name);
			if (tt <= 0) {
				break;
			}
			totalMoney = tt;
			totalCount++;
			map.put(name, c == null ? 0 : ++c);
		}

		for (String key : map.keySet()) {
			Integer count = map.get(key);
			System.out.println(key + "  次数=" + count + "  概率=" + (double) count / totalCount);
		}
		System.out.println("totalCount=" + totalCount + ",totalMoney=" + totalMoney);
	}

}
