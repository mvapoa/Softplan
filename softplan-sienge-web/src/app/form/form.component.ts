import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CalculateTransport } from '../models/calculate_transport.model';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {

  private ULR_FIND_ALL_VEHICLE: string = "http://localhost:8080/findAllVehicle";
  private ULR_CALCULATE_TRANSPORT: string = "http://localhost:8080/calculateTransport";

  vehicles: any
  distancePaved: any
  distanceUnpaved: number
  vehicle: string
  freigth: number

  constructor(private http: HttpClient) {
    this.loadAllVehicle();
  }

  public calculate() {
    if (!this.validate()) {
      alert("Alguns campos estão inválidos!");
      return false;
    }

    let param = new CalculateTransport();
    param.distancePaved = Number(this.distancePaved);
    param.distanceUnpaved = Number(this.distanceUnpaved);
    param.vehicle = this.vehicle;
    param.freigth = Number(this.freigth);
    this.calculateTransport(param);
  }

  private validate() {
    if (this.isEmpty(this.distancePaved)) {
      return false;
    }

    if (this.isEmpty(this.distanceUnpaved)) {
      return false;
    }

    if (this.isEmpty(this.vehicle)) {
      return false;
    }

    if (this.isEmpty(this.freigth)) {
      return false;
    }

    return true;
  }

  private loadAllVehicle() {
    this.http.post(this.ULR_FIND_ALL_VEHICLE, {})
      .subscribe(response => {
        if (response['status'] == 200) {
          this.vehicles = response['body'];
        }
      });
  }

  private calculateTransport(param: CalculateTransport) {
    this.http.post(this.ULR_CALCULATE_TRANSPORT, JSON.stringify(param), { headers: { 'Content-Type': 'application/json; charset=utf-8' } })
      .subscribe(response => {
        if (response['status'] == 200) {
          alert(this.getFormattedPrice(response['body']));
        } else {
          alert("As informações estão inválidas!");
        }
      });
  }

  private getFormattedPrice(price: number) {
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(price);
  }

  private isEmpty(text: any): boolean {
    return !text || text == "" || text == undefined;
  }

}
