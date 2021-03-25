import React, { Component } from "react";
import PetForm from "./petForm";
import PetTable from "./petTable";

async function deletePet(ids) {
  const requestOptions = {
    method: "DELETE",
  };
  ids.forEach((element) => {
    fetch(
      "http://localhost:8080/pets/" + element,
      requestOptions
    ).then((response) => response.json());
  });
}

class PetPage extends Component {
  state = { isLoaded: false, petForm: false, pets: [], petTitle: "Pets" };

  componentDidMount() {
    this.resetState();
  }

  resetState() {
    fetch("http://localhost:8080/owners/" + this.props.match.params.id)
      .then((res) => res.json())
      .then(
        (result) => {
          if (this.props.match.params.id) {
            const petsList = result.pets;
            const petTitle = result.firstName + "'s Pets";
            this.setState({
              pets: petsList,
              petTitle: petTitle,
            });
          }
          this.setState({
            isLoaded: true,
            owner: result,
          });
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error,
          });
        }
      )
      .then(this.handleLinks);
  }

  handlePetForm = (flag) => {
    this.setState({ petForm: flag });
    if (!flag) {
      setTimeout(() => {
        this.setState({ updateValues: null });
      }, 1000);
    }
  };

  handleAddPet = (newPet) => {
    newPet.owner = this.state.owner;
    newPet.birthDate = newPet.birthDate.toISOString().substr(0, 10);
    console.log(newPet);
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newPet),
    };
    fetch("http://localhost:8080/pets", requestOptions)
      .then((response) => response.json())
      .then((data) => this.resetState());
  };

  handleUpdatePet = (newPet) => {
    console.log(newPet);
    const requestOptions = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newPet),
    };
    fetch("http://localhost:8080/pets/" + newPet.id, requestOptions)
      .then((response) => response.json())
      .then((data) => this.resetState());
  };

  handleDeletePet = (ids) => {
    deletePet(ids).then(() => this.resetState());
  };

  handleUpdateForm = (id) => {
    this.handlePetForm(true);
    const updateValues = this.state.pets.filter((pet) => pet.id === id)[0];
    this.setState({ updateValues: updateValues });
  };

  handleLinks = () => {
    const pets = this.state.pets.map((pet) => {
      pet.link = "/visits?petId=" + pet.id;
      return pet;
    });
    this.setState({ pets: pets });
  };

  render() {
    return (
      <React.Fragment>
        <PetTable
          title={this.state.petTitle}
          pets={this.state.pets}
          setOpen={this.handlePetForm}
          onDelete={this.handleDeletePet}
          onUpdateForm={this.handleUpdateForm}
        />
        <PetForm
          onAdd={this.handleAddPet}
          open={this.state.petForm}
          setOpen={this.handlePetForm}
          onUpdate={this.handleUpdatePet}
          updateValues={this.state.updateValues}
        />
      </React.Fragment>
    );
  }
}

export default PetPage;
