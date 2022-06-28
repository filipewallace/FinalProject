import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Mods } from 'src/app/models/mods';
import { ModService } from 'src/app/services/mod.service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-mod',
  templateUrl: './mod.component.html',
  styleUrls: ['./mod.component.css']
})
export class ModComponent implements OnInit {
  editMod: null | Mods = null;
  mods: Mods[] = [];
  newMod: Mods = new Mods();
  currentRate = 0;

  constructor(private modSvc: ModService, private route: ActivatedRoute, private router: Router,config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  reload():void {
    this.modSvc.index().subscribe(
      {
        next: (mod) => {
          console.log(mod)
          this.mods = mod;
        },
        error: (problem) => {
          console.error("ModHttpComponent.reload(): error loading Mods: ");
          console.error(problem);

        }
      }
    );
  }


  deleteMod(id: number): void {

    this.modSvc.destroy(id).subscribe({
      next: (result) => {
        this.reload();
      },
      error: (err) => {
        console.error("ModComponent.deleteMod(): Error deleting Mod");
        console.error(err);
      }
    })
  };


  addMod(mod: Mods): void {
     this.modSvc.create(mod).subscribe({
      next: (result) =>{
        this.newMod = new Mods();
        this.reload()
      },
      error: (err) => {
        console.error("ModsComponent.addMod(): Error Creating a mod");
        console.error(err);
      }
    })
  };

  updateMod(mod: Mods){
    this.modSvc.update(mod).subscribe({
      next: (mod) => {
        console.log(mod);
        this.reload();
      },

      error: (err) => {
        console.error("ModComponent.updateMod(): Error updating mod");
        console.error(err);
      }
    })
  }





  open(content: any) {
    this.modalService.open(content);
  }




  ngOnInit(): void {

    this.reload()
  }

}
