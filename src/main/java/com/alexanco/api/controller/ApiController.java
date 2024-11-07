package com.alexanco.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexanco.api.AppException;
import com.alexanco.api.iso8583.ISO8583ParserManager;

@Service
public class ApiController {

    @Autowired
    private ISO8583ParserManager iso8583ParserManager;

    public HashMap<Integer, String>  parser(String iso, String type) {
        try {
			return iso8583ParserManager.parseISO2HashMap(iso, type);
		} catch (org.jpos.iso.ISOException e) {
			throw new AppException("Error to parser ISO:: " + e.getMessage());
		}
    }
}