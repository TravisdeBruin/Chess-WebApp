import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-bishop',
  templateUrl: './bishop.component.html',
  styleUrls: ['./bishop.component.scss'],
})
export class BishopComponent {
  @Input()
  blackPiece: boolean = false;

  getImage() {
    return this.blackPiece
      ? 'assets/images/pieces/bb.png'
      : 'assets/images/pieces/wb.png';
  }
}
