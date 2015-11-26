package garvanza.fm.nio;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import garvanza.fm.nio.db.Mongoi;
import garvanza.fm.nio.stt.GSettings;

public  class InvoiceFactory {
    private  long version;
    private  String reference;
    private  long invoiceType;
    private  long serial;
    private  Client client;
    private  Agent agent;
    private  Agent printedTo;
    private  Shopman shopman;
    private  InvoiceItem items[];
    private  long creationTime;
    private  long updated;
    private  InvoiceLog logs[];
    private  long total;
    private  long subTotal;
    private  long taxes;
    private  long totalValue;
    private  long debt;
    private  long agentPayment;
    private  boolean hasElectronicVersion;
    
    private	InvoiceFactory(){}
    
    public	Invoice make(HttpServletRequest request){
    	try {
    		int clientReference=new Integer(request.getParameter("clientReference"));
    		OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
    		
			String clientJson=URLDecoder.decode(request.getParameter("client"),"utf-8");
			String itemsJson=URLDecoder.decode(request.getParameter("list"),"utf-8");
			String sellerJson=URLDecoder.decode(request.getParameter("seller"),"utf-8");
			String agentJson=URLDecoder.decode(request.getParameter("agent"),"utf-8");
			String requesterJson=URLDecoder.decode(request.getParameter("requester"),"utf-8");
			String shopmanJson=URLDecoder.decode(request.getParameter("shopman"),"utf-8");
			String destinyJson=URLDecoder.decode(request.getParameter("destiny"),"utf-8");
			String args=URLDecoder.decode(request.getParameter("args"),"utf-8");
			String command=URLDecoder.decode(request.getParameter("command"),"utf-8");
			return make(clientJson, agentJson, itemsJson);
			
    	} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public	Invoice make(String clientJson, String agentJson, String itemsJson){
    	Client client = new Gson().fromJson(clientJson, Client.class);
    	Agent agent = new Gson().fromJson(clientJson, Agent.class);
    	List<InvoiceItem> invoiceItems = new Gson().fromJson(itemsJson, new TypeToken <LinkedList<Document>>(){}.getType());
    	int count=new Mongoi().doIncrement(Mongoi.REFERENCE, "{ \"reference\" : \"unique\" }", "count");
    	long serial=Long.parseLong(GSettings.get("INVOICE_SERIAL"));
    	return null;
    }
    /*public InvoiceFactory(long version, String reference, long invoiceType, long serial, Client client, Agent agent, PrintedTo printedTo, Shopman shopman, Item[] items, long creationTime, long updated, Log[] logs, long total, long subTotal, long taxes, long totalValue, long debt, long agentPayment, boolean hasElectronicVersion){
        this.version = version;
        this.reference = reference;
        this.invoiceType = invoiceType;
        this.serial = serial;
        this.client = client;
        this.agent = agent;
        this.printedTo = printedTo;
        this.shopman = shopman;
        this.items = items;
        this.creationTime = creationTime;
        this.updated = updated;
        this.logs = logs;
        this.total = total;
        this.subTotal = subTotal;
        this.taxes = taxes;
        this.totalValue = totalValue;
        this.debt = debt;
        this.agentPayment = agentPayment;
        this.hasElectronicVersion = hasElectronicVersion;
    }*/
/*
    public static final class Client {
        public final String code;
        public final String consummer;
        public final long consummerType;
        public final String address;
        public final String interiorNumber;
        public final String exteriorNumber;
        public final String suburb;
        public final String locality;
        public final String city;
        public final String country;
        public final String state;
        public final String email;
        public final String cp;
        public final String rfc;
        public final String tel;
        public final long payment;
        public final String reference;
        public final String aditionalReference;

        public Client(String code, String consummer, long consummerType, String address, String interiorNumber, String exteriorNumber, String suburb, String locality, String city, String country, String state, String email, String cp, String rfc, String tel, long payment, String reference, String aditionalReference){
            this.code = code;
            this.consummer = consummer;
            this.consummerType = consummerType;
            this.address = address;
            this.interiorNumber = interiorNumber;
            this.exteriorNumber = exteriorNumber;
            this.suburb = suburb;
            this.locality = locality;
            this.city = city;
            this.country = country;
            this.state = state;
            this.email = email;
            this.cp = cp;
            this.rfc = rfc;
            this.tel = tel;
            this.payment = payment;
            this.reference = reference;
            this.aditionalReference = aditionalReference;
        }
    }

    public static final class Agent {
        public final String code;
        public final String consummer;
        public final long consummerType;
        public final String address;
        public final String interiorNumber;
        public final String exteriorNumber;
        public final String suburb;
        public final String locality;
        public final String city;
        public final String country;
        public final String state;
        public final String email;
        public final String cp;
        public final String rfc;
        public final String tel;
        public final long payment;
        public final String reference;
        public final String aditionalReference;

        public Agent(String code, String consummer, long consummerType, String address, String interiorNumber, String exteriorNumber, String suburb, String locality, String city, String country, String state, String email, String cp, String rfc, String tel, long payment, String reference, String aditionalReference){
            this.code = code;
            this.consummer = consummer;
            this.consummerType = consummerType;
            this.address = address;
            this.interiorNumber = interiorNumber;
            this.exteriorNumber = exteriorNumber;
            this.suburb = suburb;
            this.locality = locality;
            this.city = city;
            this.country = country;
            this.state = state;
            this.email = email;
            this.cp = cp;
            this.rfc = rfc;
            this.tel = tel;
            this.payment = payment;
            this.reference = reference;
            this.aditionalReference = aditionalReference;
        }
    }

    public static final class PrintedTo {
        public final String code;
        public final String consummer;
        public final long consummerType;
        public final String address;
        public final String interiorNumber;
        public final String exteriorNumber;
        public final String suburb;
        public final String locality;
        public final String city;
        public final String country;
        public final String state;
        public final String email;
        public final String cp;
        public final String rfc;
        public final String tel;
        public final long payment;
        public final String reference;
        public final String aditionalReference;

        public PrintedTo(String code, String consummer, long consummerType, String address, String interiorNumber, String exteriorNumber, String suburb, String locality, String city, String country, String state, String email, String cp, String rfc, String tel, long payment, String reference, String aditionalReference){
            this.code = code;
            this.consummer = consummer;
            this.consummerType = consummerType;
            this.address = address;
            this.interiorNumber = interiorNumber;
            this.exteriorNumber = exteriorNumber;
            this.suburb = suburb;
            this.locality = locality;
            this.city = city;
            this.country = country;
            this.state = state;
            this.email = email;
            this.cp = cp;
            this.rfc = rfc;
            this.tel = tel;
            this.payment = payment;
            this.reference = reference;
            this.aditionalReference = aditionalReference;
        }
    }

    public static final class Shopman {
        public final long id;
        public final String name;
        public final String login;
        public final String[] permissions;

        public Shopman(long id, String name, String login, String[] permissions){
            this.id = id;
            this.name = name;
            this.login = login;
            this.permissions = permissions;
        }
    }

    public static final class Item {
        public final long quantity;
        public final long id;
        public final String hash;
        public final String code;
        public final String mark;
        public final long unitPrice;
        public final String unit;
        public final String description;
        public final long stored;
        public final long collecting;
        public final long sending;
        public final long requested;
        public final long missed;
        public final long productPriceKind;
        public final long calls;
        public final boolean disabled;
        public final boolean edited;
        public final boolean firstTimeInventored;

        public Item(long quantity, long id, String hash, String code, String mark, long unitPrice, String unit, String description, long stored, long collecting, long sending, long requested, long missed, long productPriceKind, long calls, boolean disabled, boolean edited, boolean firstTimeInventored){
            this.quantity = quantity;
            this.id = id;
            this.hash = hash;
            this.code = code;
            this.mark = mark;
            this.unitPrice = unitPrice;
            this.unit = unit;
            this.description = description;
            this.stored = stored;
            this.collecting = collecting;
            this.sending = sending;
            this.requested = requested;
            this.missed = missed;
            this.productPriceKind = productPriceKind;
            this.calls = calls;
            this.disabled = disabled;
            this.edited = edited;
            this.firstTimeInventored = firstTimeInventored;
        }
    }

    public static final class Log {
        public final String kind;
        public final long date;
        public final boolean value;
        public final String login;

        public Log(String kind, long date, boolean value, String login){
            this.kind = kind;
            this.date = date;
            this.value = value;
            this.login = login;
        }
    }
    */
}