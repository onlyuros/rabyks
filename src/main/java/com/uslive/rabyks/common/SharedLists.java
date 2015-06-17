package com.uslive.rabyks.common;

import java.util.ArrayList;

import com.uslive.rabyks.controllers.websocket.ClubNameSocket;
import com.uslive.rabyks.models.Rezervisan;

public class SharedLists {

	public static String[] clubList = {"dragstor", "terasa"};
	
	public static ArrayList<ClubNameSocket> clubNameSocketList = new ArrayList<ClubNameSocket>();
	
	public static ArrayList<Rezervisan> listaRezervacija = new ArrayList<Rezervisan>();
}