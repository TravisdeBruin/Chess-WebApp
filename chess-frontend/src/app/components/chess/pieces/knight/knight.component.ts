import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-knight',
  templateUrl: './knight.component.html',
  styleUrls: ['./knight.component.scss'],
})
export class KnightComponent {
  @Input()
  blackPiece: boolean = false;

  getImage() {
    return this.blackPiece
      ? 'assets/images/pieces/bn.png'
      : 'assets/images/pieces/wn.png';
  }
}
