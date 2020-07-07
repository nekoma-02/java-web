package by.epam.university.service.validator;

import java.text.ParseException;
import java.util.List;

public interface ApplicationValidator {

	List<String> validate(String adres, String certificate, String typeDocument, String idDocument, String seriesPassport, String numberPassport, String issuedBy, String endStudyDate) throws ParseException;
}
