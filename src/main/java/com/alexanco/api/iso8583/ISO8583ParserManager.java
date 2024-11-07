package com.alexanco.api.iso8583;

import java.util.HashMap;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.stereotype.Service;

import com.alexanco.api.AppException;
import com.alexanco.api.Constants;
import com.alexanco.api.Utils;

@Service
public class ISO8583ParserManager {

	protected HashMap<Integer, String> getISO8583ValueByFieldName(
		String isoMessage,
		String type
	) throws org.jpos.iso.ISOException {
		GenericPackager packager = new GenericPackager();
		packager.readFile(
			Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(
					Constants.FILE_PATH_PACKAGER
				)
		);

		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);

		byte[] bIsoMessage = type.equals(Constants.TYPE_PARSE_REQUEST)
			? Utils.hexStringToByteArray(isoMessage.substring(4))
			: Utils.hexStringToByteArray(isoMessage);

		isoMsg.unpack(bIsoMessage);

		HashMap<Integer, String> isoParse = new HashMap<>();

		for (int i = 1; i <= isoMsg.getMaxField(); i++) {
			if (isoMsg.hasField(i)) if (isoMsg.getString(i) != null) {
				isoParse.put(i, isoMsg.getString(i));
			}
		}

		return isoParse;
	}

	protected String getValueByFieldCode(
		String isoMessage,
		String type,
		int field
	) throws org.jpos.iso.ISOException {
		GenericPackager packager = new GenericPackager();
		packager.readFile(
			Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(
					Constants.FILE_PATH_PACKAGER
				)
		);

		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);

		byte[] bIsoMessage = type.equals(Constants.TYPE_PARSE_REQUEST)
			? Utils.hexStringToByteArray(isoMessage.substring(4))
			: Utils.hexStringToByteArray(isoMessage);

		isoMsg.unpack(bIsoMessage);

		if (isoMsg.hasField(field)) {
			if (isoMsg.getString(field) != null) {
				return isoMsg.getString(field);
			}
		}

		//   throw new AppException("Cannot get field");
		throw new AppException("Error to get ISO value");
	}

	protected String getMTI(String isoMessage, String type)
		throws org.jpos.iso.ISOException {
		GenericPackager packager = new GenericPackager();
		packager.readFile(
			Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(
					Constants.FILE_PATH_PACKAGER
				)
		);

		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);

		byte[] bIsoMessage = type.equals(Constants.TYPE_PARSE_REQUEST)
			? Utils.hexStringToByteArray(isoMessage.substring(4))
			: Utils.hexStringToByteArray(isoMessage);

		isoMsg.unpack(bIsoMessage);

		return isoMsg.getMTI();
	}

	protected String replaceField(
		String isoMessage,
		String type,
		int field,
		String value
	) throws org.jpos.iso.ISOException {
		GenericPackager packager = new GenericPackager();
		packager.readFile(
			Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(
					Constants.FILE_PATH_PACKAGER
				)
		);

		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);

		byte[] bIsoMessage = type.equals(Constants.TYPE_PARSE_REQUEST)
			? Utils.hexStringToByteArray(isoMessage.substring(4))
			: Utils.hexStringToByteArray(isoMessage);

		isoMsg.unpack(bIsoMessage);

		if (isoMsg.hasField(field)) {
            if (isoMsg.getString(field) != null) {
                isoMsg.set(field, value);
            }
        }

		byte[] bIsoMsg = isoMsg.pack();

		String iso = Utils.byteArrayToString(bIsoMsg, 0, bIsoMsg.length);

		return iso;
	}

	protected String removeField(
        String isoMessage,
        String type,
        int ...field
    ) throws org.jpos.iso.ISOException {
        GenericPackager packager = new GenericPackager();
        packager.readFile(
            Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream(
                    Constants.FILE_PATH_PACKAGER
                )
        );

        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);

        byte[] bIsoMessage = type.equals(Constants.TYPE_PARSE_REQUEST)
            ? Utils.hexStringToByteArray(isoMessage.substring(4))
            : Utils.hexStringToByteArray(isoMessage);

        isoMsg.unpack(bIsoMessage);
		
		for (int f : field) {
			if (isoMsg.hasField(f)) {
				if (isoMsg.getString(f) != null) {
					isoMsg.unset(f);
				}
			}
		}

        byte[] bIsoMsg = isoMsg.pack();

        String iso = Utils.byteArrayToString(bIsoMsg, 0, bIsoMsg.length);

        return iso;
    }

	public HashMap<Integer, String> parseISO2HashMap(
		String autdata,
		String type
	) throws org.jpos.iso.ISOException {
		return getISO8583ValueByFieldName(autdata, type);
	}

	public String getValueFromFieldISO(String autdata, String type, int field)
		throws org.jpos.iso.ISOException {
		return getValueByFieldCode(autdata, type, field);
	}

	public String getMTIValue(String autdata, String type)
		throws org.jpos.iso.ISOException {
		return getMTI(autdata, type);
	}

	public String replaceFieldISO(
		String isoMessage,
		String type,
		int field,
		String value
	) throws org.jpos.iso.ISOException {
		return replaceField(isoMessage, type, field, value);
	}

	public String printISO(String iso, String type) throws ISOException{
		return removeField(iso, type, Constants.ISO_FIELD_PINBLOCK, Constants.ISO_FIELD_CARD_NUMBER, Constants.ISO_FIELD_PAN);
	  }
}
