package com.customers.sqllite.util;

import com.customers.sqllite.dto.CustomerDto;
import com.customers.sqllite.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Utils {

    @Autowired
    ModelMapper modelMapper;

    @Resource(name="populateCountries")
    public Map<String, String> countriesMap;

    // method to validate number
    public static boolean validateNumber (String phoneNumber) {
        String patterns = "^(\\(237\\)\\ ?[2368]\\d{7,8}$)" + "|^(\\(251\\)\\ ?[1-59]\\d{8}$)"
                + "|^(\\(212\\)\\ ?[5-9]\\d{8}$)" + "|^(\\(258\\)\\ ?[28]\\d{7,8}$)" + "|^(\\(256\\)\\ ?\\d{9}$)";
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return true;
        } else
            return false;
    }

    public String getCountryCodeBasedOnCountry(String country){

        if(!countriesMap.containsKey(country)){

            throw new NotFoundException("this country is not in the list");
        }
        String code =countriesMap.get(country);
        return code;
    }

    public static boolean checkKeys (Map<String, String> customers){
        for ( String key : customers.keySet() ) {
            if(key.equals("country")  || key.equals("state") || key.equals("number")){
                continue ;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public <S, T> List<T> entityListToDto (List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static String convertListToJsonString(List<CustomerDto> studentList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(studentList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
