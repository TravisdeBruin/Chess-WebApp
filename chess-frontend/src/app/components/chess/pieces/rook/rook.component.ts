import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-rook',
  templateUrl: './rook.component.html',
  styleUrls: ['./rook.component.scss'],
})
export class RookComponent {
  @Input()
  blackPiece: boolean = false;

  getImage() {
    return this.blackPiece
      ? 'assets/images/pieces/br.png'
      : 'assets/images/pieces/wr.png';
  }
}
