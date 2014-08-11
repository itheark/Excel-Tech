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
import com.greycodes.excel14.nontechnical.FragmentBestManager;
import com.greycodes.excel14.nontechnical.FragmentCSI;
import com.greycodes.excel14.nontechnical.FragmentGameZone;
import com.greycodes.excel14.nontechnical.FragmentInstantPhotography;
import com.greycodes.excel14.nontechnical.FragmentSpiderWeb;
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
		case 889:
			tv =FragmentHashInclude.tv;
			break;
		case 890:
			tv =FragmentWebBots.tv;
			break;
		case 891:
			tv =FragmentLOC.tv;
			break;
		case 892:
			tv =FragmentHackMaster.tv;
			break;
		case 893:
			tv =FragmentFourOneTwenty.tv;
			break;
		case 894:
			tv =FragmentAlgorithms.tv;
			break;
		case 895:
			tv =FragmentSoYouThink.tv;
			break;
		case 896:
			tv =FragmentExtrinsicity.tv;
			break;
		case 897:
			tv =FragmentDefuse.tv;
			break;
		case 898:
			tv =FragmentCircuim.tv;
			break;
		case 899:
			tv =FragmentLumiere.tv;
			break;
		case 900:
			tv =FragmentExtundoprodigo.tv;
			break;
		case 901:
			tv =EaventuraFragment.tv;
			break;
		case 902:
			tv =FragmentRobowars.tv;
			break;
		case 903:
			tv =FragmentTerrainMaster.tv;
			break;
		case 904:
			tv =FragmentLifeLine.tv;
			break;
		case 905:
			tv =Fragmentkryptos.tv;
			break;
		case 906:
			tv =FragmentDalalBull.tv;
			break;
		case 907:
			tv =FragmentPapyrusOfAni.tv;
			break;
		case 908:
			tv =FragmentBestManager.tv;
			break;
		case 909:
			tv =FragmentCSI.tv;
			break;
		case 910:
			tv =FragmentGameZone.tv;
			break;
		case 911:
			tv =FragmentSpiderWeb.tv;
			break;
		case 912:
			tv =FragmentInstantPhotography.tv;
			break;
		case 913:
			tv =DotIssueFragment.tv;
			break;
		case 914:
			tv =TedXMecFragment.tv;
			break;
		case 915:
			tv =SeminarFragment.tv;
			break;
		case 916:
			tv =ExhibitionFragment.tv;
			break;
		case 917:
			tv =WorkshopFragment.tv;
			break;
		
		case 919:
			tv =IbetoFragment.tv;
			break;
		case 920:
			tv =IbetoJrFragment.tv;
			break;
		case 921:
			tv =DevconFragment.tv;
			break;
		case 922:
			tv =MarketManiaFragment.tv;
			break;
		case 923:
			tv =TagURItFragment.tv;
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
