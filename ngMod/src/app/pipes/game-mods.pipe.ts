import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'gameMods'
})
export class GameModsPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
