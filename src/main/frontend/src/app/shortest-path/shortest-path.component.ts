import { Component, OnInit } from '@angular/core';
import { ShortestPathService } from './shortest-path.service';
import { PlanetListResponse, Planet, ShortestPathRequest, BaseResponse } from '../shared/model/interfaces';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { element } from 'protractor';

@Component({
  selector: 'app-shortest-path',
  templateUrl: './shortest-path.component.html',
  styleUrls: ['./shortest-path.component.css']
})
export class ShortestPathComponent implements OnInit {

  public planets: Planet[] = [];

  public form: FormGroup;

  public source: Planet;
  public destination: Planet;

  public path: string = '';

  constructor(private _formBuilder: FormBuilder, private shortestPathService: ShortestPathService) {
    this.form = this._formBuilder.group({
      source: ['', Validators.compose([Validators.required])],
      destination: ['', Validators.compose([Validators.required])]
    });
  }

  ngOnInit(): void {
    this.shortestPathService.getListOfPlanets().subscribe((response: PlanetListResponse) => {
      this.planets = response.planets
    }, error => {
      alert(error.response.message)
    })
    console.log((document.getElementById('submit') as HTMLButtonElement).disabled)
  }

  public onFormSubmit(): void {

    this.reset();

    const request: ShortestPathRequest = {
      source: this.form.value.source,
      destination: this.form.value.destination
    }

    this.source = this.findPlanet(request.source);
    this.destination = this.findPlanet(request.destination);

    this.shortestPathService.calculateShortestPath(request).subscribe((response: PlanetListResponse) => {

      this.path += response.planets[0].planetName + ' (' + response.planets[0].planetId + ')';

      for (let index = 1; index < response.planets.length; index++) {
        this.path += (' -> ' + response.planets[index].planetName + ' (' + response.planets[index].planetId + ')')
      }
    }, (error: BaseResponse) => {
      alert(error.response.message)
    })
  }

  private findPlanet(id: string): Planet {
    return this.planets.find(({planetId}) => planetId === id);
  }

  public reset() {
    this.path = '';
  }
}
