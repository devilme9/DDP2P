/* ------------------------------------------------------------------------- */
/*   Copyright (C) 2012 Marius C. Silaghi
		Author: Marius Silaghi: msilaghi@fit.edu
		Florida Tech, Human Decision Support Systems Laboratory
   
       This program is free software; you can redistribute it and/or modify
       it under the terms of the GNU Affero General Public License as published by
       the Free Software Foundation; either the current version of the License, or
       (at your option) any later version.
   
      This program is distributed in the hope that it will be useful,
      but WITHOUT ANY WARRANTY; without even the implied warranty of
      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
      GNU General Public License for more details.
  
      You should have received a copy of the GNU Affero General Public License
      along with this program; if not, write to the Free Software
      Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.              */
/* ------------------------------------------------------------------------- */

package data;

import hds.ASNSyncRequest;

import java.util.Calendar;

import config.DD;

import util.Util;
import ASN1.ASN1DecoderFail;
import ASN1.ASNObj;
import ASN1.Decoder;
import ASN1.Encoder;
public
class D_OrgParams extends ASNObj{
	public int certifMethods; //ENUM
	public String hash_org_alg; //Printable
	//public byte[] hash_org; //OCT STR
	public Calendar creation_time;
	public String creator_global_ID;
	public String category; //UTF8
	public byte[] certificate; //OCT STR
	public String[] default_scoring_options; // SEQ OF UTF8
	public String instructions_new_motions; // UTF8
	public String instructions_registration; //UTF8
	public String description; //UTF8
	public String[] languages; // SEQ of Printable
	public D_OrgParam[] orgParam; // SEQ of 
	public String toString() {
		return "Org Params: ["+
		";\n certifMethods="+Util.nullDiscrim(certifMethods)+
		";\n hash_org_alg="+Util.nullDiscrim(hash_org_alg)+
		//";\n hash_org="+((hash_org==null)?"NULL":"\""+Util.byteToHex(hash_org, ":")+"\"")+
		";\n creation_time="+((creation_time==null)?"null":Encoder.getGeneralizedTime(creation_time))+
		";\n creator="+Util.trimmed(creator_global_ID)+
		";\n category="+Util.nullDiscrim(category)+
		";\n certificate="+Util.nullDiscrim(Util.byteToHex(certificate, ":"))+
		";\n default_scoring_options=["+Util.nullDiscrim(Util.concat(default_scoring_options, ","))+"]"+
		";\n instructions_new_motions="+Util.nullDiscrim(instructions_new_motions)+
		";\n instructions_registration="+Util.nullDiscrim(instructions_registration)+
		";\n description="+Util.nullDiscrim(description)+
		";\n languages=["+Util.concat(languages, ":")+"]"+
		";\n orgParam=["+Util.concat(orgParam, "\t")+"]"+
		"\n]";
	}
	public Encoder getEncoder() {
		if(ASNSyncRequest.DEBUG)System.out.println("Encoding OrgParams: "+this);
		Encoder enc = new Encoder().initSequence();
		enc.addToSequence(new Encoder(certifMethods));
		if(hash_org_alg != null) enc.addToSequence(new Encoder(hash_org_alg, false).setASN1Type(DD.TAG_AC0));
		if(creation_time != null) enc.addToSequence(new Encoder(creation_time).setASN1Type(DD.TAG_AC1));
		if(creator_global_ID != null) enc.addToSequence(new Encoder(creator_global_ID, false).setASN1Type(DD.TAG_AC2));
		if(category != null) enc.addToSequence(new Encoder(category).setASN1Type(DD.TAG_AC3));
		if(certificate != null) enc.addToSequence(new Encoder(certificate).setASN1Type(DD.TAG_AC4));
		if(default_scoring_options != null) enc.addToSequence(Encoder.getStringEncoder(default_scoring_options, Encoder.TAG_UTF8String).setASN1Type(DD.TAG_AC5));
		if(instructions_new_motions != null) enc.addToSequence(new Encoder(instructions_new_motions).setASN1Type(DD.TAG_AC6));
		if(instructions_registration != null) enc.addToSequence(new Encoder(instructions_registration).setASN1Type(DD.TAG_AC7));
		if(description != null) enc.addToSequence(new Encoder(description).setASN1Type(DD.TAG_AC10));
		if(languages != null) enc.addToSequence(Encoder.getStringEncoder(languages, Encoder.TAG_PrintableString).setASN1Type(DD.TAG_AC8));
		if(orgParam!=null)enc.addToSequence(Encoder.getEncoder(orgParam).setASN1Type(DD.TAG_AC9));
		//enc.addToSequence(new Encoder(hash_org));
		if(ASNSyncRequest.DEBUG)System.out.println("Encoded OrgParams: "+this);
		return enc;
	}
	@Override
	public D_OrgParams decode(Decoder decoder) throws ASN1DecoderFail {
		if(ASNSyncRequest.DEBUG)System.out.println("DEcoding OrgParams: "+this);
		Decoder dec = decoder.getContent();
		certifMethods = dec.getFirstObject(true).getInteger().intValue();
		if(dec.getTypeByte()==DD.TAG_AC0) hash_org_alg = dec.getFirstObject(true).getStringAnyType();
		if(dec.getTypeByte()==DD.TAG_AC1) creation_time = dec.getFirstObject(true).getGeneralizedTimeCalenderAnyType();
		if(dec.getTypeByte()==DD.TAG_AC2) creator_global_ID = dec.getFirstObject(true).getStringAnyType();
		if(dec.getTypeByte()==DD.TAG_AC3) category = dec.getFirstObject(true).getStringAnyType();
		if(dec.getTypeByte()==DD.TAG_AC4) certificate= dec.getFirstObject(true).getBytesAnyType();
		if(dec.getTypeByte()==DD.TAG_AC5) default_scoring_options=dec.getFirstObject(true).getSequenceOf(Encoder.TAG_UTF8String);
		if(dec.getTypeByte()==DD.TAG_AC6) instructions_new_motions = dec.getFirstObject(true).getStringAnyType();
		if(dec.getTypeByte()==DD.TAG_AC7) instructions_registration = dec.getFirstObject(true).getStringAnyType();
		if(dec.getTypeByte()==DD.TAG_AC10) description = dec.getFirstObject(true).getStringAnyType();
		if(dec.getTypeByte()==DD.TAG_AC8) languages=dec.getFirstObject(true).getSequenceOf(Encoder.TAG_PrintableString);
		if(dec.getTypeByte()==DD.TAG_AC9) orgParam = dec.getFirstObject(true).getSequenceOf(Encoder.TYPE_SEQUENCE, new D_OrgParam[]{}, new D_OrgParam());
		if(dec.getFirstObject(false)!=null) throw new ASN1DecoderFail("Extra Objects in decoder: "+decoder.dumpHex());
		//hash_org= dec.getFirstObject(true).getBytes(Encoder.TAG_OCTET_STRING);
		if(ASNSyncRequest.DEBUG)System.out.println("DEcoded OrgParams: "+this);
		return this;
	}
}
