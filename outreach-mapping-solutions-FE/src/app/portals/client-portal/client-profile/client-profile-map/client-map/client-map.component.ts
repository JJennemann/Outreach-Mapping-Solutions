import { Component, OnInit } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat, toLonLat } from 'ol/proj';
import OSM from 'ol/source/OSM';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import { Style, Fill, Stroke, Circle } from 'ol/style';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import Overlay from 'ol/Overlay';
import { Coordinate } from 'ol/coordinate';

@Component({
  selector: 'app-client-map',
  templateUrl: './client-map.component.html',
  styleUrls: ['./client-map.component.css']
})
export class ClientMapComponent implements OnInit {
  map: Map;
  vectorSource: VectorSource;
  vectorLayer: VectorLayer<VectorSource>;
  overlay: Overlay;
  markers: Array<{ coordinate: Coordinate, info: string }> = [];

  ngOnInit(): void {
    this.vectorSource = new VectorSource();
    this.vectorLayer = new VectorLayer({
      source: this.vectorSource,
      style: new Style({
        image: new Circle({
          radius: 6,
          fill: new Fill({ color: 'red' }),
          stroke: new Stroke({ color: 'black', width: 2 }),
        }),
      }),
    });

    this.map = new Map({
      target: 'map-container',
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
        this.vectorLayer,
      ],
      view: new View({
        center: fromLonLat([-90.1994, 38.6270]),
        zoom: 10,
      }),
    });

    this.overlay = new Overlay({
      element: document.getElementById('popup'),
      autoPan: true,
    });
    this.map.addOverlay(this.overlay);

    this.map.on('click', (event) => {
      const coordinate = event.coordinate;
      this.showPopupForm(coordinate);
    });

    // Handle "Save" and "Cancel" button clicks
    document.getElementById('save-button').addEventListener('click', () => {
      const markerInfo = (document.getElementById('marker-info') as HTMLInputElement).value;
      this.addMarker(this.overlay.getPosition(), markerInfo); // Add the marker with the popup's position
      this.hidePopup();
    });

    document.getElementById('cancel-button').addEventListener('click', () => {
      this.hidePopup();
    });
  }

  showPopupForm(coordinate: Coordinate) {
    const popup = this.overlay.getElement();
    popup.style.display = 'block';
    this.overlay.setPosition(coordinate);
  }

  hidePopup() {
    const popup = this.overlay.getElement();
    popup.style.display = 'none';
  }

  addMarker(coordinate: Coordinate, info: string) {
    const markerFeature = new Feature({
      geometry: new Point(coordinate),
    });
    this.markers.push({ coordinate, info });
    this.vectorSource.addFeature(markerFeature);
  }
}


// // import { Component, OnInit } from '@angular/core';

// // import OSM from 'ol/source/OSM';
// // import TileLayer from 'ol/layer/Tile';
// // import {Map, View} from 'ol';
// // import {fromLonLat} from 'ol/proj';


// // @Component({
// //   selector: 'app-client-profile-map',
// //   templateUrl: './client-profile-map.component.html',
// //   styleUrls: ['./client-profile-map.component.css']
// // })
// // export class ClientProfileMapComponent implements OnInit {
// //   map: Map;

// //   ngOnInit(): void {
// //     this.map = new Map({
// //       target: 'map-container',
// //       layers: [
// //         new TileLayer({
// //           source: new OSM(),
// //         }),
// //       ],
// //       view: new View({
// //         center: fromLonLat([-90.1994, 38.6270]),
// //         zoom: 10,
// //       }),
// //     });
// //   }
// // }

// import { Component, OnInit } from '@angular/core';
// import Map from 'ol/Map';
// import View from 'ol/View';
// import TileLayer from 'ol/layer/Tile';
// import { fromLonLat, toLonLat } from 'ol/proj';
// import OSM from 'ol/source/OSM';
// import VectorLayer from 'ol/layer/Vector';
// import VectorSource from 'ol/source/Vector';
// import { Style, Fill, Stroke, Circle } from 'ol/style';
// import Feature from 'ol/Feature';
// import Point from 'ol/geom/Point';
// import Overlay from 'ol/Overlay';
// import {Coordinate }from 'ol/coordinate';

// @Component({
//   selector: 'app-client-profile-map',
//   templateUrl: './client-profile-map.component.html',
//   styleUrls: ['./client-profile-map.component.css']
// })
// export class ClientProfileMapComponent implements OnInit {
//   map: Map;
//   vectorSource: VectorSource;
//   vectorLayer: VectorLayer<VectorSource>;
//   overlay: Overlay;

//   markers: Array<{ coordinate: Coordinate, info: string }> = [];

//   ngOnInit(): void {
//     this.vectorSource = new VectorSource();
//     this.vectorLayer = new VectorLayer({
//       source: this.vectorSource,
//       style: new Style({
//         image: new Circle({
//           radius: 6,
//           fill: new Fill({ color: 'red' }),
//           stroke: new Stroke({ color: 'black', width: 2 }),
//         }),
//       }),
//     });

//     this.map = new Map({
//       target: 'map-container',
//       layers: [
//         new TileLayer({
//           source: new OSM(),
//         }),
//         this.vectorLayer,
//       ],
//       view: new View({
//         center: fromLonLat([-90.1994, 38.6270]),
//         zoom: 10,
//       }),
//     });

//     this.overlay = new Overlay({
//       element: document.getElementById('popup'),
//       autoPan: true,
//     });
//     this.map.addOverlay(this.overlay);

//     this.map.on('click', (event) => {
//       const coordinate = event.coordinate;
//       this.showPopup(coordinate);
//       const clickedCoordinate = event.coordinate;
//       const updatedMarkerInfo = 'New marker location'; // You can update the marker's information
//       this.updateMarkerPosition(clickedCoordinate, updatedMarkerInfo);
//     });


//   }

//   addMarker(coordinate: Coordinate, info: string) {
//     const markerFeature = new Feature({
//       geometry: new Point(coordinate),
//     });
//     this.markers.push({ coordinate, info });
//     this.vectorSource.addFeature(markerFeature);
//   }

//   updateMarkerPosition(coordinate: Coordinate, info: string) {
//     // Remove the existing marker
//     this.vectorSource.clear();

//     // Add a new marker with the updated coordinates
//     this.addMarker(coordinate, info);
//   }

//   showPopup(coordinate: Coordinate) {
//     const info = this.findMarkerInfo(coordinate);
//     if (info) {
//       const popup = this.overlay.getElement();
//       popup.innerHTML = info;
//       this.overlay.setPosition(coordinate);
//     }
//   }

//   findMarkerInfo(coordinate: Coordinate) {
//     const marker = this.markers.find(
//       (m) => toLonLat(m.coordinate)[0] === toLonLat(coordinate)[0] &&
//              toLonLat(m.coordinate)[1] === toLonLat(coordinate)[1]
//     );
//     return marker ? marker.info : null;
//   }
// }
