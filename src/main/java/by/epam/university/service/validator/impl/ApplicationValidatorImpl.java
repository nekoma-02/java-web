package by.epam.university.service.validator.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.epam.university.service.validator.ApplicationValidator;
import by.epam.university.service.validator.ValidatorParameters;

public class ApplicationValidatorImpl implements ApplicationValidator {

	private static final String DATE_FORMAT = "yyyy-mm-dd";
	
	@Override
	public List<String> validate(String adress, String certificate, String typeDocument, String idDocument,
			String seriesPassport, String numberPassport, String issuedBy, String endStudyDate) throws ParseException {
		List<String> validation = new ArrayList<String>();
		
		if (adress == null ) {
			validation.add(ValidatorParameters.INVALID_ADRESS);
		}
		
		if (certificate == null || (Integer.parseInt(certificate) > 0 && Integer.parseInt(certificate) <= 100)) {
			validation.add(ValidatorParameters.INVALID_CERTIFICATE);
		}
		
		if (typeDocument == null ) {
			validation.add(ValidatorParameters.INVALID_TYPE_DOCUMENT);
		}
		
		if (idDocument == null ) {
			validation.add(ValidatorParameters.INVALID_ID_DOCUMENT);
		}
		
		if (seriesPassport == null ) {
			validation.add(ValidatorParameters.INVALID_SERIES_PASSPORT);
		}
		
		if (numberPassport == null ) {
			validation.add(ValidatorParameters.INVALID_NUMBER_PASSPORT);
		}
		
		if (issuedBy == null ) {
			validation.add(ValidatorParameters.INVALID_ISSUED_BY);
		}
		
		if (endStudyDate == null || isValidFormat(endStudyDate)) {
			validation.add(ValidatorParameters.INVALID_END_STUDY_DATE);
		}
		
		return validation;
	}
	
	
	private static boolean isValidFormat(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		java.util.Date udate = formatter.parse(date);
		if (!date.equals(formatter.format(udate))) {
			return false;
		}
		return true;
	}

}
