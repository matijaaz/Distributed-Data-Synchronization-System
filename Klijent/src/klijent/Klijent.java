         /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package klijent;

import entities.Kategorija;
import entities.Korisnik;
import entities.Mesto;
import entities.Video;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Matija
 */
public class Klijent {

    /**
     * @param args the command line arguments
     */
    
    private static final String podsistem1 = "http://localhost:8080/CentralniServer/api/podsistem1/" ;
    private static final String podsistem2 = "http://localhost:8080/CentralniServer/api/podsistem2/" ;
    private static final String podsistem3 = "http://localhost:8080/CentralniServer/api/podsistem3/" ;
    
    
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        boolean end = false;
        
        while (!end) {
            try {
            ispisiFunkcionalnosti();
             System.out.print("Unesite redni broj funkcionalnosti koju zelite da izvrsite : ");
             Scanner scanner = new Scanner(System.in);
             int request = scanner.nextInt();
            
            switch(request) {
        
            case 1 : {
                 
                System.out.print("Unesite naziv grada : ");
                String naziv = scanner.next();
                String response = client.target(podsistem1 + "grad")
                .request().post(Entity.entity(naziv, MediaType.TEXT_PLAIN)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
                
            }
            
            case 2 : {
                Mesto mesto = new Mesto();
                Korisnik korisnik = new Korisnik();
                System.out.print("Unesite ime : ");
                korisnik.setIme(scanner.next());
                System.out.print("Unesite mejl : ");
                korisnik.setMejl(scanner.next());
                System.out.print("Unesite godiste : ");
                korisnik.setGodiste(scanner.nextInt());
                System.out.print("Unesite pol : ");
                korisnik.setPol(scanner.next());
                System.out.print("Unesite naziv mesta : ");
                mesto.setNaziv(scanner.next());
                korisnik.setSifM(mesto);
               
                String response = client.target(podsistem1 + "korisnik")
                .request(MediaType.APPLICATION_JSON).post(Entity.json(korisnik)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 
                 
                
                break;
            }
            
            case 3 : {
                System.out.print("Unesite sifru korisnika : ");
                int id = scanner.nextInt();
                System.out.print("Unesite novu email adresu korisnika : ");
                String email = scanner.next();
                 String response = client.target(podsistem1 + "korisnik").path("{idKor}")
                 .path("{email}").resolveTemplate("idKor", id).resolveTemplate("email", email)
                 .request().put(Entity.entity("Funkcija", MediaType.TEXT_PLAIN)).readEntity(String.class);
                
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                break;
            }
            
            case 4 : {
                System.out.print("Unesite sifru korisnika : ");
                int id = scanner.nextInt();
                System.out.print("Unesite novu sifru mesta za korisnika : ");
                int sifra = scanner.nextInt();
                 String response = client.target(podsistem1 + "korisnik")
                 .path("{sifM}").resolveTemplate("sifM", sifra)
                 .request().put(Entity.entity(id, MediaType.TEXT_PLAIN)).readEntity(String.class);
                
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                break;
                
            }
            
            case 5 : {
            
                System.out.print("Unesite naziv kategorije : ");
                String naziv = scanner.next();
                String response = client.target(podsistem2 + "kategorija")
                .request().post(Entity.entity(naziv, MediaType.TEXT_PLAIN)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
            
            }
            
            case 6 : {
                
                Video video= new Video();
                System.out.print("Unesite naziv : ");
                video.setNaziv(scanner.next());
                System.out.print("Unesite trajanje(u sekundama) : ");
                video.setTrajanje(scanner.nextInt());
                System.out.print("Unesite ID vlasnika : ");
                int id = scanner.nextInt();
               
                String response = client.target(podsistem2 + "video").path("{vlasnik}").resolveTemplate("vlasnik", id)
                .request(MediaType.APPLICATION_JSON).post(Entity.json(video)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 
                 
                
                break;
            }
            
            case 7 : {
            
                System.out.print("Unesite sifru videa : ");
                int id = scanner.nextInt();
                System.out.print("Unesite nov naziv : ");
                String naziv = scanner.next();
                 String response = client.target(podsistem2 + "video").path("{video}")
                 .path("{naziv}").resolveTemplate("video", id).resolveTemplate("naziv", naziv)
                 .request().put(Entity.entity("Funkcija", MediaType.TEXT_PLAIN)).readEntity(String.class);
                
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                break;
            }
            
            case 8 : {
                
                System.out.print("Unesite sifru videa : ");
                int id = scanner.nextInt();
                System.out.print("Unesite naziv kategorije : ");
                String naziv = scanner.next();
                 String response = client.target(podsistem2 + "video").path("{video}").resolveTemplate("video", id)
                 .request().put(Entity.entity(naziv, MediaType.TEXT_PLAIN)).readEntity(String.class);
                
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                break;
            }
            case 9 : {
                
                System.out.print("Unesite cenu paketa : ");
                int cena = scanner.nextInt();
               
                String response = client.target(podsistem3 + "paket")
                .request().post(Entity.entity(cena, MediaType.TEXT_PLAIN)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
            }
            
            case 10 : {
                
                System.out.print("Unesite sifru paketa : ");
                int id = scanner.nextInt();
                System.out.print("Unesite novu cenu paketa : ");
                int cena = scanner.nextInt();
                 String response = client.target(podsistem3 + "paket").path("{paket}").resolveTemplate("paket", id)
                 .request().put(Entity.entity(cena, MediaType.TEXT_PLAIN)).readEntity(String.class);
                
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                break;
            }
            
            case 11 : {
            
                System.out.print("Unesite sifru korisnika : ");
                int id = scanner.nextInt();
                System.out.print("Unesite sifru paketa : ");
                int paket = scanner.nextInt();
               
                String response = client.target(podsistem3 + "pretplata").path("{idKor}").resolveTemplate("idKor", id)
                .request().post(Entity.entity(paket, MediaType.TEXT_PLAIN)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
            
            }
            
            case 12 : {
                
                System.out.print("Unesite sifru korisnika : ");
                int id = scanner.nextInt();
                System.out.print("Unesite sifru videa : ");
                int video = scanner.nextInt();
                System.out.print("Unesite sekund od kog je zapoceto gledanje : ");
                int sekund = scanner.nextInt();
                System.out.print("Unesite koliko sekundi videa je odgledano : ");
                int odgledano = scanner.nextInt();
                
               
                String response = client.target(podsistem3 + "gledanje").path("{idKor}").path("{sekundi}").path("{odgledano}").
                 resolveTemplate("idKor", id).resolveTemplate("sekundi", sekund).resolveTemplate("odgledano", odgledano)
                .request().post(Entity.entity(video, MediaType.TEXT_PLAIN)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
                
            }
            case 13 : {
                
                // @Path("ocena/{idKor}/{ocena}")
                System.out.print("Unesite sifru korisnika : ");
                int id = scanner.nextInt();
                System.out.print("Unesite sifru videa : ");
                int video = scanner.nextInt();
                System.out.print("Unesite ocenu : ");
                int ocena = scanner.nextInt();
                
               
                String response = client.target(podsistem3 + "ocena").path("{idKor}").path("{ocena}").
                 resolveTemplate("idKor", id).resolveTemplate("ocena", ocena)
                .request().post(Entity.entity(video, MediaType.TEXT_PLAIN)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
            
            }
            
            case 14 : {
                //@Path("ocena/{ocena}/{video}")
                System.out.print("Unesite sifru korisnika : ");
                int id = scanner.nextInt();
                System.out.print("Unesite sifru videa : ");
                int video = scanner.nextInt();
                System.out.print("Unesite novu ocenu : ");
                int ocena = scanner.nextInt();
                
               
                String response = client.target(podsistem3 + "ocena").path("{ocena}").path("{video}").
                 resolveTemplate("ocena", ocena).resolveTemplate("video", video)
                .request().put(Entity.entity(id, MediaType.TEXT_PLAIN)).readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
            }
            
            case 15 : {
                //@Path("ocena/{video}/{idKor}")
                System.out.print("Unesite sifru korisnika : ");
                int id = scanner.nextInt();
                System.out.print("Unesite sifru videa : ");
                int video = scanner.nextInt();
                
               
                String response = client.target(podsistem3 + "ocena").path("{video}").path("{idKor}").
                 resolveTemplate("video", video).resolveTemplate("idKor", id)
                .request().delete().readEntity(String.class);
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                 break;
            
            }
            case 16 : {
            
                System.out.print("Unesite sifru videa : ");
                int id = scanner.nextInt();
                System.out.print("Unesite sifru vlasnika videa : ");
                String naziv = scanner.next();
                 String response = client.target(podsistem2 + "video").path("{video}").path("{idKor}").
                 resolveTemplate("video", id).resolveTemplate("idKor",naziv)
                 .request().delete().readEntity(String.class);
                
                 System.out.println(response);
                 System.out.println();
                 Thread.sleep(1000);
                break;
            
            }
            case 17 : {
                
                List<Mesto> mesta = client.target(podsistem1 + "mesto").request().get(new GenericType<List<Mesto>>(){});
                if (mesta.isEmpty()) {
                    System.out.println("Nema gradova u bazi.");
                    break;
                }
                
                System.out.println();
                System.out.print(String.format("%-15s", "SifM"));
                System.out.print(String.format("%-15s", "Naziv"));
                System.out.println();
                
                for(Mesto m : mesta) {
                    System.out.print(String.format("%-15s", m.getSifM()));
                    System.out.print(String.format("%-15s", m.getNaziv()));
                    System.out.println();
                }
                
                System.out.println();
                Thread.sleep(1000);
                break;
            }
            
            case 18: {
                List<Korisnik> korisnici = client.target(podsistem1 + "korisnik").request().get(new GenericType<List<Korisnik>>(){});
                if (korisnici.isEmpty()) {
                    System.out.println("Nema korisnika u bazi.");
                    break;
                }
                
                System.out.println();
                System.out.print(String.format("%-25s", "SifK"));
                System.out.print(String.format("%-25s", "Ime"));
                System.out.print(String.format("%-25s", "Mejl"));
                System.out.print(String.format("%-25s", "Godiste"));
                System.out.print(String.format("%-25s", "Naziv mesta"));
                System.out.print(String.format("%-25s", "Pol"));
                System.out.println();
                
                for (Korisnik k : korisnici) {
                    System.out.print(String.format("%-25s", k.getSifK()));
                    System.out.print(String.format("%-25s", k.getIme()));
                    System.out.print(String.format("%-25s", k.getMejl()));
                    System.out.print(String.format("%-25s", k.getGodiste()));
                    System.out.print(String.format("%-25s", k.getSifM().getNaziv()));
                    System.out.print(String.format("%-25s", k.getPol()));
                    System.out.println();
                }
                
                System.out.println();
                Thread.sleep(1000);
                break;
            }
            
            case 19 : {
                List<Kategorija> kategorije = client.target(podsistem2 + "kategorija").request().get(new GenericType<List<Kategorija>>(){});
                if (kategorije.isEmpty()) {
                    System.out.println("Nema kategorija u bazi.");
                    break;
                }
                
                System.out.println();
                System.out.print(String.format("%-15s", "SifK"));
                System.out.print(String.format("%-15s", "Naziv"));
                System.out.println();
                
                for(Kategorija m : kategorije) {
                    System.out.print(String.format("%-15s", m.getSifK()));
                    System.out.print(String.format("%-15s", m.getNaziv()));
                    System.out.println();
                }
                
                System.out.println();
                Thread.sleep(1000);
                break;
            }
            
            case 20 : {
                
                List<Video> vids = client.target(podsistem2 + "video").request().get(new GenericType<List<Video>>(){});
                if (vids.isEmpty()) {
                    System.out.println("Nema kategorija u bazi.");
                    break;
                }
                
                System.out.println();
                System.out.print(String.format("%-25s", "SifV"));
                System.out.print(String.format("%-25s", "Naziv"));
                System.out.print(String.format("%-25s", "Trajanje(sekunde)"));
                System.out.print(String.format("%-25s", "DatumVreme"));
                System.out.print(String.format("%-25s", "IdVlasnika"));
                System.out.print(String.format("%-25s", "Vlasnik"));
                System.out.println();
                
                for(Video m : vids) {
                    System.out.print(String.format("%-25s", m.getSifV()));
                    System.out.print(String.format("%-25s", m.getNaziv()));
                    System.out.print(String.format("%-25s", m.getTrajanje()));
                    System.out.print(String.format("%-25s", m.getDatumVreme()));
                    System.out.print(String.format("%-25s", m.getVlasnik().getSifK()));  
                    System.out.print(String.format("%-25s", m.getVlasnik().getIme()));
                    System.out.println();
                }
                
                System.out.println();
                Thread.sleep(1000);
                break;
                
            }
            
            case 21 : {
                System.out.print("Unesite sifru videa : ");
                int id = scanner.nextInt();
                List<Kategorija> kategorije = client.target(podsistem2 + "kategorija").path("{video}").
                        resolveTemplate("video",id).request().get(new GenericType<List<Kategorija>>(){});
                if (kategorije.isEmpty()) {
                    System.out.println("Nema kategorija za taj video ili video ne postoji.");
                    System.out.println();
                    break;
                }
                
                System.out.println();
                System.out.print(String.format("%-15s", "SifK"));
                System.out.print(String.format("%-15s", "Naziv"));
                System.out.println();
                
                for(Kategorija m : kategorije) {
                    System.out.print(String.format("%-15s", m.getSifK()));
                    System.out.print(String.format("%-15s", m.getNaziv()));
                    System.out.println();
                }
                
                System.out.println();
                Thread.sleep(1000);
                break;
            }
            
            case 26 : end = true; break;
            
            default : System.out.println("Unesite jedan od ponudjenih brojeva !"); System.out.println();Thread.sleep(1000);
             }
            }catch (InterruptedException ex) {
                    Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
                    } 
        
        }
        
        
        
        
        
       
    }
    
    
    private static void ispisiFunkcionalnosti() {
        
        System.out.println("FUNKCIONALNOSTI :");
        System.out.println("-------------------------------------------");
        System.out.println(" 1. Kreiraj grad.");
        System.out.println(" 2. Kreiraj korisnika.");
        System.out.println(" 3. Promeni mejl korisnika.");
        System.out.println(" 4. Promeni mesto korisnika.");
        System.out.println(" 5. Kreiraj kategoriju.");
        System.out.println(" 6. Kreiraj video.");
        System.out.println(" 7. Promeni naziv videu.");
        System.out.println(" 8. Dodaj kategoriju videu.");
        System.out.println(" 9. Kreiraj paket.");
        System.out.println("10. Promeni mesecnu cenu paketa.");
        System.out.println("11. Kreiraj pretplatu korisnika na paket.");
        System.out.println("12. Kreiraj gledanje.");
        System.out.println("13. Kreiraj ocenu za video.");
        System.out.println("14. Izmeni ocenu videa.");
        System.out.println("15. Obrisi ocenu videa.");
        System.out.println("16. Obrisi video.");
        System.out.println("17. Dohvati mesta.");
        System.out.println("18. Dohvati korisnike.");
        System.out.println("19. Dohvati kategorije.");
        System.out.println("20. Dohvati video snimke.");
        System.out.println("21. Dohvati kategorije za video.");
        System.out.println("26. Zavrsi program.");
        System.out.println("-------------------------------------------");
    
    
    }
    
}
