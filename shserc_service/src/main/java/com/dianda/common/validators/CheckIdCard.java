package com.dianda.common.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckIdCard implements ConstraintValidator<ICheckIdCard, String> {

	private static final int[] ADDRESS_CODES = {11, 12, 13, 14, 15, 21, 22, 23, 31, 32, 33, 34, 35, 36, 37, 41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54, 61, 62, 63, 64, 65, 71, 81, 82};

	private static final String[] ALLOWED_CODES = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};

	private static final int[] Wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};

	private static final String DATE_FORMAT = "yyyyMMdd";


	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		String address = s.substring(0, 6);
		String birthday = s.substring(6, 8);
		boolean checkResult = checkAddress(address) &&
				checkBirthday(birthday) &&
				checkLastCode(s);

		return checkResult;
	}

	private boolean checkAddress(String address) {
		// 310115
		int addressNo = Integer.parseInt(address);
		int code = addressNo / 10000;
		int start = 0, end = ADDRESS_CODES.length - 1, middle = 0;
		while (end >= start) {
			middle = (start + end) / 2;
			if (code == ADDRESS_CODES[middle]) {
				return true;
			} else if (code > ADDRESS_CODES[middle]) {
				start = middle;
			} else if (code < ADDRESS_CODES[middle]) {
				end = middle;
			}
		}
		return false;
	}

	private boolean checkBirthday(String birthday) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			Date date = simpleDateFormat.parse(birthday);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private boolean checkSeq(String seq) {
		try {
			int value = Integer.parseInt(seq);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean checkLastCode(String value) {
		String lastCode = value.substring(17, 1);
		char[] all = value.toCharArray();

		int val = 0;
		for (int i = 0; i < 17; i++) {
			val += Wi[i] * (all[i] - '0');
		}

		if (lastCode.toLowerCase() != ALLOWED_CODES[val % 11]) {
			return false;
		}

		return true;
	}

}
