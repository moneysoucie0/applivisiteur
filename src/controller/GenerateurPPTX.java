package controller;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class GenerateurPPTX {
	
	public GenerateurPPTX(String Dispo,String NomMedoc,String Effet,String contreIndication, File Path) throws IOException {
		NomMedoc=NomMedoc.replaceAll("/", "");
		NomMedoc=NomMedoc.replaceAll(",", "");		
		NomMedoc=NomMedoc.replaceAll("\r\n", "");
		
		String nomFichier= "/"+ NomMedoc+".pptx";
		File Background =new File("img/fond_transp.png");
		
		File ImgMedoc =new File("img/doliprane.png");
		File file =new File(Path+nomFichier);
		
		newFile(file);		
		
		if (Dispo=="1") {
			
			Effet=textOpti(Effet,240);
			contreIndication= textOpti(contreIndication,240);
			
			Dispo1(file,NomMedoc,Effet,contreIndication,ImgMedoc,Background);
		}
		if (Dispo=="2") {
			Effet=textOpti(Effet,260);
			contreIndication=textOpti(contreIndication,260);
			
			Dispo2(file,NomMedoc,Effet,contreIndication,ImgMedoc,Background);
		}
		if (Dispo=="3") {
			Dispo3(file,NomMedoc,ImgMedoc,Background);
		}
		if (Dispo=="4") {
			Effet=textOpti(Effet,300);
			contreIndication=textOpti(contreIndication,300);
			
			Dispo4(file,NomMedoc,Effet,contreIndication,Background);
		}
		
		
	
		
		   }
	/* Crée le fichier .pptx */
	public static void newFile(File file) throws IOException {
		 //créer un objet FileOutputStream pour enregistrer le document PPT
		 XMLSlideShow xml = new XMLSlideShow();
	     FileOutputStream fis = new FileOutputStream(file);
	     //sauvegarder le fichier
	     xml.write(fis);

	     
	     System.out.println(file);
	     System.out.println("Fichier en cours de création");
	     fis.close();
	     xml.close();
	      
		  }
	/* Créé un slide dans le powerpoint généré ci-dessus */
	 public static void newSlide(File fichier,File ImgBackground,Integer numSlide) throws IOException {
		 FileInputStream inputstream=new FileInputStream(fichier);
		 XMLSlideShow xml = new XMLSlideShow(inputstream);
		 XSLFSlideMaster slideMaster = xml.getSlideMasters().get(0);
		 XSLFSlideLayout TitleEtContent = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
		 xml.createSlide(TitleEtContent);
		 
		 
		 FileOutputStream fos = new FileOutputStream(fichier);
	      
	      xml.write(fos);
	      
	      newSlideImg(fichier,ImgBackground,"Background",numSlide);
	      
	      fos.close(); 
	      xml.close();
	 }
	 /* Ajoute un nouveau titre et paragraphe à un slide */
	 public static void modifSlide(File fichier,String title,String paragraphe,Integer numSlide,String position) throws IOException{
	      FileInputStream inputstream=new FileInputStream(fichier);
	      XMLSlideShow xml = new XMLSlideShow(inputstream);
	      
	      List<XSLFSlide> slides = xml.getSlides();
	      XSLFSlide slide = (XSLFSlide) slides.get(numSlide);
	      
	      XSLFTextShape zoneTitre = slide.getPlaceholder(0);
	      XSLFTextShape zoneText =slide.getPlaceholder(1);
	      zoneTitre.clearText();
	      zoneText.clearText();
	      
	      XSLFTextParagraph leTitre=zoneTitre.addNewTextParagraph();
	      XSLFTextRun run1 = leTitre.addNewTextRun();
	      
	      XSLFTextParagraph leText=zoneText.addNewTextParagraph();
	      XSLFTextRun run2 = leText.addNewTextRun();
	      
          run1.setText(title);
          run2.setText(paragraphe);
          
          if(position=="gauche") {
        	  zoneText.setAnchor(new Rectangle(0,150,350,150));
          }
          if(position=="droite") {
        	  zoneText.setAnchor(new Rectangle(380,150,350,150));
          }
          if(position=="centre") {
        	  zoneText.setAnchor(new Rectangle(150,150,500,200));
          }
          
        	  //Gauche zoneText.setAnchor(new Rectangle(0,150,350,150));
        	  //Droite zoneText.setAnchor(new Rectangle(400,150,350,150));  

          run2.setFontSize((double) 24); 
	      
	      
	    
	      //ajouter des slides à la présentation
	      FileOutputStream fis = new FileOutputStream(fichier);
	      xml.write(fis);
	      System.out.println("texte ajouté");
	      fis.close();
	      xml.close();
	   }
	 
	 /* Insère l'image du médicament (en base de donnée) dans le slide */
	 public static void newSlideImg(File fichier,File img, String position,Integer numSlide) throws IOException{
		 FileInputStream inputstream=new FileInputStream(fichier);
		 XMLSlideShow xml = new XMLSlideShow(inputstream);
		 List<XSLFSlide> slides = xml.getSlides();
	      XSLFSlide slide = (XSLFSlide) slides.get(numSlide);

		 byte[] picture = IOUtils.toByteArray(new FileInputStream(img));
	      
	      //ajouter l'image � la présentation
	      PictureData picdata = xml.addPicture(picture, PictureData.PictureType.PNG);
	      
	      
	      //créer un slide pour cette image ajoutée
	     XSLFPictureShape pic = slide.createPicture(picdata);
	     
	     // on prend notre image pour récupérer ses dimentions, car getWidth n'existe pas, on transforme les dimentions en string
	     String stringAnchor= pic.getAnchor().toString();
	     
	     //Permet de récupérer la largeur de l'image, via les dimentions transformer en string
	     int widthImg =Integer.parseInt(stringAnchor.substring(stringAnchor.indexOf('w',7)+2,stringAnchor.indexOf('.',stringAnchor.indexOf('w',7))));
	     int heightImg=Integer.parseInt(stringAnchor.substring(stringAnchor.indexOf('h')+2,stringAnchor.indexOf('.',stringAnchor.indexOf('h'))));


	     //System.out.println(xml.getPageSize());
	     
	     int widthSlide=(int) xml.getPageSize().getWidth();
	     int heightSlide=(int) xml.getPageSize().getHeight();
	     if(position=="centre") {
	     // Pour le centre il faut diviser width et height de la slide par 2 moins la width et la height de l'image
	     pic.setAnchor(new Rectangle((widthSlide-widthImg)/2,(heightSlide-heightImg)/2,widthImg,heightImg));
	     System.out.println("Image ajoutée et centrée !");
	     
	     }
	     if(position=="gauche") {
	    	 pic.setAnchor(new Rectangle(10,(heightSlide-heightImg)/2,widthImg,heightImg));
		     System.out.println("Image ajoutée et positionée à gauche !");
		  
	     }
	     if(position=="droite") {
	    	 pic.setAnchor(new Rectangle(widthSlide-widthImg-10,(heightSlide-heightImg)/2,widthImg,heightImg));
		     System.out.println("Image ajoutée et positionée à droite !");

	     }
	     if(position=="Background") {
	    	 pic.setAnchor(new Rectangle(0,0,widthSlide,heightSlide));
	    	
	     }
	      FileOutputStream fos = new FileOutputStream(fichier);
	      
	      
	      xml.write(fos);
	      
	      fos.close(); 
	      xml.close();
	 }
	 
	 /* Disposition 1 du slide */
	 public static void Dispo1(File file,String NomMedoc,String TextMedoc,String TextMedoc2,File imgMedoc,File imgBackground) throws IOException{
		
		 
		 for (int i = 0; i < 4; i++) {
				newSlide(file,imgBackground,i);
			}
		 
		 //slide 1
			modifSlide(file,NomMedoc,"",0,"gauche");
			//slide 2
			modifSlide(file,NomMedoc,TextMedoc,1,"gauche");
			newSlideImg(file,imgMedoc,"droite",1);
			//slide 3
			modifSlide(file,NomMedoc,TextMedoc2,2,"gauche");
			newSlideImg(file,imgMedoc,"droite",2);
			//slide 4
			modifSlide(file,"\n\n\nC'était la présentation de "+NomMedoc+" merci pour votre attention !","",3,"gauche");
			
		 
	 }
	 /* Disposition 2 du slide */
	 public static void Dispo2(File file,String NomMedoc,String TextMedoc,String TextMedoc2,File imgMedoc,File imgBackground) throws IOException{
			
		 
		 for (int i = 0; i < 4; i++) {
				newSlide(file,imgBackground,i);
			}
		 
		//slide 1
		modifSlide(file,NomMedoc,"",0,"droite");
		//slide 2
		modifSlide(file,NomMedoc,TextMedoc,1,"droite");
		newSlideImg(file,imgMedoc,"gauche",1);
		//slide 3
		modifSlide(file,NomMedoc,TextMedoc2,2,"droite");
		newSlideImg(file,imgMedoc,"gauche",2);
		//slide 4
		modifSlide(file,"\n\n\nC'était la présentation de "+NomMedoc+" merci pour votre attention !","",3,"droite");
				
			 
		 }


		 /* Disposition 3 du slide */
	 public static void Dispo3(File file,String NomMedoc,File imgMedoc,File imgBackground) throws IOException{
		 for (int i = 0; i < 3; i++) {
			 newSlide(file,imgBackground,i);
			 }
		 // slide 1
		 modifSlide(file,NomMedoc,"",0,"droite");
		 //slide 2
		 modifSlide(file,NomMedoc,"",1,"droite");
		 newSlideImg(file,imgMedoc,"centre",1);
		 //slide 3
		 modifSlide(file,"\n\n\nC'était la présentation de "+NomMedoc+" merci pour votre attention !","",2,"droite");
		 
	 }

	 /* Disposition 4 du slide */
	 public static void Dispo4(File file,String NomMedoc,String TextMedoc,String TextMedoc2,File imgBackground) throws IOException{
		 for (int i = 0; i < 4; i++) {
				newSlide(file,imgBackground,i);
			}
		 
		//slide 1
		modifSlide(file,NomMedoc,"",0,"centre");
		//slide 2
		modifSlide(file,NomMedoc,TextMedoc,1,"centre");
		//slide 3
		modifSlide(file,NomMedoc,TextMedoc2,2,"centre");
		//slide 4
		modifSlide(file,"\n\n\nC'était la présentation de "+NomMedoc+" merci pour votre attention !","",3,"centre");
		 
	 }
	 
	 
	 public static String textOpti(String text,int nbrCaractere) {
		 if(text.length()>nbrCaractere) {
				char[] c_arr = text.toCharArray();
				text=new String(c_arr,0,nbrCaractere-3);
				text=text+"...";
			}
		 return text;
		 
	 }
			
}


