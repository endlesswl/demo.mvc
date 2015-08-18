package com.palm.lingcai.util;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;


/**
 * 球
 * @author LDL
 *
 */
public class BallsUtils {
	/**
	 * 体彩 随机生成大乐透投注号码
	 * 
	 * @return
	 */
	public static String randomDLT() {
		Set<Integer> beginSet = new TreeSet<Integer>();
		Set<Integer> afterSet = new TreeSet<Integer>();
		// 随机产生预测红球
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			if (beginSet.size() < 5) {
				beginSet.add((int) random.nextInt(35) + 1);
			}
			if (afterSet.size() < 2) {
				afterSet.add((int) random.nextInt(12) + 1);
			}
		}
		StringBuffer result = new StringBuffer();
		result.append("[");
		for (Integer i : beginSet) {
			if (i < 10) {
				result.append("0" + i).append(",");
			} else {
				result.append(i).append(",");
			}
		}

		result.deleteCharAt(result.length() - 1).append("]/[");

		for (Integer i : afterSet) {
			if (i < 10) {
				result.append("0" + i).append(",");
			} else {
				result.append(i).append(",");
			}
		}
		result.deleteCharAt(result.length() - 1).append("]");

		return result.toString();
	}

	/**
	 * 体彩 随机生成七星彩投注号码
	 * 
	 * @return
	 */
	public static String randomQXC() {
		int[] numbers = randomArray(0, 9, 7);
		StringBuffer result = new StringBuffer();
		for (int i : numbers) {
			result.append("[").append(i).append("]/");
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	/**
	 * 随机指定范围内N个不重复的数 在初始化的无重复待选数组中随机产生一个数放入结果中，
	 * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 然后从len-2里随机产生下一个随机数，如此类推
	 * 
	 * @param max
	 *            指定范围最大值
	 * @param min
	 *            指定范围最小值
	 * @param n
	 *            随机数个数
	 * @return int[] 随机数结果集
	 */
	public static int[] randomArray(int min, int max, int n) {
		int len = max - min + 1;

		if (max < min || n > len) {
			return null;
		}

		// 初始化给定范围的待选数组
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}

		int[] result = new int[n];
		Random rd = new Random();
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// 待选数组0到(len-2)随机一个下标
			index = Math.abs(rd.nextInt() % len--);
			// 将随机到的数放入结果集
			result[i] = source[index];
			// 将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
			source[index] = source[len];
		}
		return result;
	}

	/**
	 * 福彩 随机生成七乐彩投注号码
	 * 
	 * @return
	 */

	public static String randomQLC() {
		int[] numbers = randomArray(1, 30, 7);
		StringBuffer result = new StringBuffer();
		for (int i : numbers) {
			result.append(i).append(",");
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	/**
	 * 福彩 随机生成双色球投注号码
	 * 
	 * @return
	 */
	public static String randomSSQ() {
		Set<Integer> set = new TreeSet<Integer>();
		// 随机产生预测红球
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			if (set.size() < 6) {
				set.add((int) random.nextInt(33) + 1);
			}
		}
		// 随机产生预测蓝球
		int blueBall = random.nextInt(16) + 1;
		StringBuffer result = new StringBuffer();
		int size = set.size();
		for (Integer i : set) {
			if (i < 10) {
				result.append("0" + i).append(",");
			} else {
				result.append(i).append(",");
			}
		}

		result.deleteCharAt(result.length() - 1);
		if (blueBall < 10) {
			result.append(":").append("0" + blueBall);
		} else {
			result.append(":").append(blueBall);
		}
		return result.toString();
	}

	/**
	 * 福彩 随机生成双色球红球投注号码
	 * 
	 * @return
	 */
	public static String randomRedSSQ() {
		Set<Integer> set = new TreeSet<Integer>();
		// 随机产生预测红球
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			if (set.size() < 6) {
				set.add((int) random.nextInt(33) + 1);
			}
		}
		// 组装
		StringBuffer result = new StringBuffer();
		for (Integer i : set) {
			if (i < 10) {
				result.append("0" + i).append(",");
			} else {
				result.append(i).append(",");
			}
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}
	
	
	/**
	 * 格式化SSQ开奖号,篮球冒号分隔
	 * @param ball
	 * @return
	 */
	public static String formatSSQBall(String ball) {
		try {
			if (StringUtils.isBlank(ball) || ball.indexOf(":") >= 0
					|| ball.length() < 20) {
				return ball;
			}
			String redBalls = ball.substring(0, 17);
			String blueBall = ball.replace(redBalls, "").substring(1);
			ball = redBalls + ":" + blueBall;
			return ball;
		} catch (Exception e) {
			return ball;
		}
	}
	
	/**
	 * 检查是否单式投注
	 */
	public boolean isSingleModle(String gameId,String ball) {
		
																																			
		
		return true;
	}

	public static void main(String[] args) {
	}
}
