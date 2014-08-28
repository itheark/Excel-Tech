package com.greycodes.excel14;

import com.greycodes.excel14.biomedevents.FragmentLifeLine;
import com.greycodes.excel14.conference.ExhibitionFragment;
import com.greycodes.excel14.conference.SeminarFragment;
import com.greycodes.excel14.conference.WorkshopFragment;
import com.greycodes.excel14.csevents.FragmentAlgorithms;
import com.greycodes.excel14.csevents.FragmentFourOneTwenty;
import com.greycodes.excel14.csevents.FragmentHackMaster;
import com.greycodes.excel14.csevents.FragmentHashInclude;
import com.greycodes.excel14.csevents.FragmentLOC;
import com.greycodes.excel14.csevents.FragmentSoYouThink;
import com.greycodes.excel14.csevents.FragmentWebBots;
import com.greycodes.excel14.ecevents.FragmentCircuim;
import com.greycodes.excel14.ecevents.FragmentDefuse;
import com.greycodes.excel14.ecevents.FragmentExtrinsicity;
import com.greycodes.excel14.eeeevents.EaventuraFragment;
import com.greycodes.excel14.eeeevents.FragmentExtundoprodigo;
import com.greycodes.excel14.eeeevents.FragmentLumiere;
import com.greycodes.excel14.excelpro.MarketManiaFragment;
import com.greycodes.excel14.excelpro.TagURItFragment;
import com.greycodes.excel14.general.FragmentDalalBull;
import com.greycodes.excel14.general.FragmentPapyrusOfAni;
import com.greycodes.excel14.general.Fragmentkryptos;
import com.greycodes.excel14.initiatives.DevconFragment;
import com.greycodes.excel14.initiatives.IbetoFragment;
import com.greycodes.excel14.initiatives.IbetoJrFragment;
import com.greycodes.excel14.initiatives.OrganicFarmingFragment;
import com.greycodes.excel14.nontechnical.AlleyDunk;
import com.greycodes.excel14.nontechnical.ByzantineFragment;
import com.greycodes.excel14.nontechnical.DefactoFragment;
import com.greycodes.excel14.nontechnical.FragmentBestManager;
import com.greycodes.excel14.nontechnical.FragmentCSI;
import com.greycodes.excel14.nontechnical.FragmentGameZone;
import com.greycodes.excel14.nontechnical.FragmentInstantPhotography;
import com.greycodes.excel14.nontechnical.FragmentSpiderWeb;
import com.greycodes.excel14.nontechnical.FunZoneFragment;
import com.greycodes.excel14.nontechnical.TikiTakaFragment;
import com.greycodes.excel14.nontechnical.TreasureHuntFragment;
import com.greycodes.excel14.robotics.FragmentRobowars;
import com.greycodes.excel14.robotics.FragmentTerrainMaster;
import com.greycodes.excel14.talkseries.DotIssueFragment;
import com.greycodes.excel14.talkseries.TedXMecFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

public class Misc {
Context context;
	public Misc(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void call(String number){
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+number));
		context.startActivity(intent);
	}
	
	public void settext(int eid,String text){
		TextView tv =null;
		switch(eid){
		case 1000:
			tv =FragmentHashInclude.tv;
			break;
		case 1001:
			tv =FragmentWebBots.tv;
			break;
		case 1002:
			tv =FragmentLOC.tv;
			break;
		case 1003:
			tv =FragmentHackMaster.tv;
			break;
		case 1004:
			tv =FragmentFourOneTwenty.tv;
			break;
		case 1005:
			tv =FragmentAlgorithms.tv;
			break;
		case 1006:
			tv =FragmentSoYouThink.tv;
			break;
		case 1007:
			tv =FragmentExtrinsicity.tv;
			break;
		case 1008:
			tv =FragmentDefuse.tv;
			break;
		case 1009:
			tv =FragmentCircuim.tv;
			break;
		case 1010:
			tv =FragmentLumiere.tv;
			break;
		case 1011:
			tv =FragmentExtundoprodigo.tv;
			break;
		case 1012:
			tv =EaventuraFragment.tv;
			break;
		case 1013:
			tv =FragmentRobowars.tv;
			break;
		case 1014:
			tv =FragmentTerrainMaster.tv;
			break;
		case 1015:
			tv =FragmentLifeLine.tv;
			break;
		case 1016:
			tv =Fragmentkryptos.tv;
			break;
		case 1017:
			tv =FragmentDalalBull.tv;
			break;
		case 1018:
			tv =FragmentPapyrusOfAni.tv;
			break;
		case 1019:
			tv =FragmentBestManager.tv;
			break;
		case 1020:
			tv =FragmentCSI.tv;
			break;
		case 1021:
			tv =FragmentGameZone.tv;
			break;
		case 1022:
			tv =FragmentSpiderWeb.tv;
			break;
		case 1023:
			tv =FragmentInstantPhotography.tv;
			break;
		case 1024:
			tv =TikiTakaFragment.tv;
			break;
		case 1025:
			tv =DefactoFragment.tv;
			break;
		case 1026:
			tv =AlleyDunk.tv;
			break;
		case 1027:
			tv =ByzantineFragment.tv;
			break;
		case 1028:
			tv =TreasureHuntFragment.tv;
			break;
		case 1029:
			tv =FunZoneFragment.tv;
			break;
		case 1030:
			tv =DotIssueFragment.tv;
			break;
		case 1031:
			tv =TedXMecFragment.tv;
			break;
		case 1032:
			tv =SeminarFragment.tv;
			break;
		case 1033:
			tv =ExhibitionFragment.tv;
			break;
		case 1034:
			tv =WorkshopFragment.tv;
			break;
		
		case 1035:
			tv =IbetoFragment.tv;
			break;
		case 1036:
			tv =IbetoJrFragment.tv;
			break;
		case 1037:
			tv =DevconFragment.tv;
			break;
		case 1039:
			tv =MarketManiaFragment.tv;
			break;
		case 1040:
			tv =TagURItFragment.tv;
			break;
		case 1038:
			tv =OrganicFarmingFragment.tv;
			break;
			
			
		}
		try {
			tv.setText(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
