package com.colorfuldays.commu;

import com.colorfuldays.utils.LogUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by JITAO on 2016/9/12.
 * <p>
 * For init the Ksoap's request http call
 */
public class KsoapInit {

	static final String M_ARG = "ArgComKey";
	static final String M_SUB_ARG = "Arg";
	protected static HttpTransportSE httpSE;
	protected static SoapSerializationEnvelope soapserial;
	static SoapObject soapObject;
	static SoapObject subObject;

	public KsoapInit() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "KsoapInit";
	}

	public static void initKsoap(String opt, int timeOut) {

		httpSE = new HttpTransportSE(ServerUtils.getServerUrl(), timeOut);
		httpSE.debug = true;
		soapObject = new SoapObject(ServerUtils.getPACE(), opt);
		soapObject.addProperty(M_ARG, String.valueOf(ServerUtils.getComKey()));
		LogUtils.i("<--- KsoapInit ---> the soapObject is " + soapObject);

		soapserial = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		soapserial.bodyOut = soapObject;
		soapserial.dotNet = true;
	}

	public static void initSubKsoap(String nodeName, String subNodeName, String optionName, int timeOut) {

		httpSE = new HttpTransportSE(ServerUtils.getServerUrl(), timeOut);
		httpSE.debug = true;
		soapObject = new SoapObject(ServerUtils.getPACE(), nodeName);
		soapObject.addProperty(M_ARG, String.valueOf(ServerUtils.getComKey()));

		subObject = new SoapObject("", M_SUB_ARG);
		subObject.addProperty(subNodeName, optionName);
		soapObject.addSoapObject(subObject);
		LogUtils.i("<--- KsoapInit ---> the soapObject is " + soapObject);

		soapserial = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		soapserial.bodyOut = soapObject;
		soapserial.dotNet = true;
	}
}
