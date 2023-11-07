import { Component, OnInit } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';


@Component({
  selector: 'app-client-profile-map',
  templateUrl: './client-profile-map.component.html',
  styleUrls: ['./client-profile-map.component.css']
})
export class ClientProfileMapComponent implements OnInit {
  map: Map;

  ngOnInit(): void {
    this.map=new Map({
      target: 'map-container',
      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],
      view: new View({
        center: [0,0],
        zoom: 2
      })
    });
  }
}
