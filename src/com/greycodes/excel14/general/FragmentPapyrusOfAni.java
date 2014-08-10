package com.greycodes.excel14.general;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;


public class FragmentPapyrusOfAni extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.general_papyrusofani, container, false);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 intro=(ImageView)rootView.findViewById(R.id.intro);
		 rules =(ImageView)rootView.findViewById(R.id.rules);
		 format =(ImageView)rootView.findViewById(R.id.event);
		 
		 intro.setOnClickListener(this);
		 rules.setOnClickListener(this);
		 format.setOnClickListener(this);
		return rootView;
		
		
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if(v.equals(intro))
		{
			updateTextValue("“Vision without action is merely a dream”\nIt is time to titillate your grey cells, bring out innovative and ingenious ideas and unleash it to the world, ’cos it awaits a change! Papyrus of ani provides you the right platform to impact the scientific world with your brilliant thoughts and win exciting cash prizes! \n\n"	);
			
		}
		if (v.equals(rules))
		{
			updateTextValue("There will be two phases for the event. The initial phase is the online submission of the abstract. The abstracts will be verified by a panel and the best ones will be shortlisted for the next phase. The final phase is the presentation which will be conducted at EXCEL 2014 at the time allotted for each category. There will be 4 categories under which participants can compete. They include:\n1.     Electronics and Communication and Biomedical Engineering.\n2.     Electrical and Electronics Engineering.\n3.     Computer science Engineering.\n4.     General  (papers from mechanical, civil or non technical papers related to physics, maths etc.)\nThe participants will be judged by a panel for each category.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("1.Team Size - 3 members (maximum)\nRules are subject to change at any point in time. All rules will be explained at the event as well. \n Submission of abstract on or before 10/9/2014 to <poa@excelmec.org>\n2.   Abstract format will be available in the website.\n3.   After evaluation, the selected candidates will be informed via email on or before 18th September.\n4.  The number of participants in each team should not exceed 3.\n5.   Any paper that does not fall into the category of topics (suggested below) , but are innovative and original will also be encouraged.\n6.  A team cannot send more than 2 entries.\n7. Only students pursuing their graduation and post-graduation can participate.\nPHASE II\n1.   Only the submitted topics can be presented.\n2.   3 hard copies of the final paper have to be submitted in IEEE format.\n3.   Presentations have to be in PowerPoint format only and the number of slides should not exceed 25.\n4.   Total time allotted for presentation is 15 minutes: 12 minute presentation+3 minute interaction.\n5.   Medium of presentation must be in English.\n6.   Decision of judges will be final and binding.\n7.   Excel 2014 team reserves the right to take decisions in case of any disputes.\nTOPICS:Teams can submit abstracts under any of the following categories(examples of paper abstracts under each category) -\nELECTRONICS AND COMMUNICATION AND BIOMEDICAL ENGINEERING            \nExamples -\n1.    Nanoelectronics\n2.    Digital image processing\n3.    Humanoid Robotics\n4.    Wearable  and flexible electronics\n5.    Power Devices, Power Electronics Design issues\n6.    Micro energy Harvesting\n7.    virtual keyboards and displays\n8.    memristors and its applications.\n9.    Plasmonics and spintronics\n10.    Biochips\n11.    Treating cardiac disease with catheter-based tissue heating\n12.    Dual energy X-ray absorptiometer\n13.    Medical imaging\n14.    CorDECT\n15.    Medical imaging detection,acquisition,analysis and processing\n16.    Wireless technology in Health care\nCOMPUTER SCIENCE\nExamples -\n1.	Quantum Computin\n2.	Big data\n3.	Cloud computing\n4.	5g wireless\n5.	Lifi\n6.	Money pad\n7.	Google app engine\n8.	Web operating system\n9.	Solid state drive\n10.	Material Design\n11.	Natural Language Processing\n12.	Machine learning\n13.	Bitcoin\nELECTRICAL AND ELECTRONICS\nExamples -\n1 .Intelligent Inverter for Solar Power Harnessing\n2. Micro-grid\n3. EV charging using Renewable Energy\n4. Hy-Wire\n5. Smart Grid\n6. Pre-paid metering\n7. Reliability Issues with PV systems\n8. Wind Generation Forecast\n9. Unified Power Quality Conditioner\n10. Evaluation of Grid level storage for integration of Renewable Energy\nGENERAL\nAny other topic that does not come under the above  categories are also welcome.\nExample :\nPapers from mechanical, civil or non technical papers related to physics, maths etc.\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}
