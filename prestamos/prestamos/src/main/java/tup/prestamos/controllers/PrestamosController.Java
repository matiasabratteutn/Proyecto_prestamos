
package tup.prestamos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tup.prestamos.models.*;
import tup.prestamos.repositories.PrestamosRepository;

@RestController

@RequestMapping("")
public class PrestamosController {

  @Autowired
  private PrestamosRepository PrestamosRepository;


  @PostMapping("/add") 
  public String addNewPrestamos(@RequestParam int Id, @RequestParam Double monto){
    

   

   double cuota_tres = monto * 0.015;
   double cuota_seis = monto * 0.020;
   double cuota_nueve = monto * 0.025;
   double cuota_doce = monto * 0.030;
   double cuota_docho = monto * 0.050;

   Prestamos n = new Prestamos();
    n.setId(Id);
    n.setMonto(monto);
    n.setTres_cuotas(cuota_tres);
    n.setSeis_cuotas(cuota_seis);
    n.setNueve_cuotas(cuota_nueve);
    n.setDoce_cuotas(cuota_doce);
    n.setDieciocho_cuotas(cuota_docho);
    PrestamosRepository.save(n);
    return "Saved";

  }



  
  @PostMapping("/delete/{id}") 
  public String deleteprestamoById(@PathVariable int id) {
  
    PrestamosRepository.deleteById(id);
    return "Deleted";
  }

  @GetMapping("/{id}")
  public String findprestamoById(@PathVariable int id) {
  
    return PrestamosRepository.findById(id).toString();
  }

  @GetMapping("/all")
  public String getAllPrestamos() {

    Iterable<Prestamos> iterable = PrestamosRepository.findAll();

 /*
    String resp = """
          <style>
            #prestamos {"
              font-family: Arial, Helvetica, sans-serif;
              border-collapse: collapse;
              width: 100%;
            }
            #prestamos td, #prestamos th {
              border: 1px solid #ddd;
              padding: 8px;
            }
            #prestamos tr:nth-child(even){background-color: #f2f2f2;}
            #prestamos tr:hover {background-color: #ddd;}
            #prestamos th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #04AA6D;
              color: white;
            }
          </style>
          <table id ='prestamos'>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Email</th>
            </tr>
        """;
            
           */
        
            String resp = "";
        
   
    for (Prestamos prestamo : iterable) {
      resp += "<tr>"
          + "<td>" + prestamo.getId() + "</td>"
          + "<td>" + prestamo.getMonto() + "</td>"
          + "<td>" + prestamo.getTres_cuotas() + "</td>"
          + "<td>" + prestamo.getSeis_cuotas() + "</td>"
          + "<td>" + prestamo.getNueve_cuotas() + "</td>"
          + "<td>" + prestamo.getDoce_cuotas() + "</td>"
          + "<td>" + prestamo.getDieciocho_cuotas() + "</td>"
          + "</tr>";
    }
    return resp + "</table>";
  }

}

  



