import React, { Component } from "react";
import VetForm from "./vetForm";
import VetTable from "./vetTable";

async function deleteVet(ids) {
  const requestOptions = {
    method: "DELETE",
  };
  ids.forEach((element) => {
    fetch(
      "http://localhost:8080/vets/" + element,
      requestOptions
    ).then((response) => response.json());
  });
}

class VetPage extends Component {
  state = { isLoaded: false, vetsForm: false, vets: [] };

  componentDidMount() {
    this.resetState();
  }

  resetState = () => {
    fetch("http://localhost:8080/vets")
      .then((res) => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            vets: result,
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
  };

  handleVetsForm = (flag) => {
    this.setState({ vetsForm: flag });
    if (!flag) {
      setTimeout(() => {
        this.setState({ updateValues: null });
      }, 1000);
    }
  };

  handleAddVet = (newVet) => {
    console.log(newVet);
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newVet),
    };
    fetch("http://localhost:8080/vets", requestOptions)
      .then((response) => response.json())
      .then((data) => this.resetState());
  };

  handleUpdateVet = (newVet) => {
    console.log(newVet);
    const requestOptions = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newVet),
    };
    fetch("http://localhost:8080/vets/" + newVet.id, requestOptions)
      .then((response) => response.json())
      .then((data) => this.resetState());
  };

  handleUpdateForm = (id) => {
    this.handleVetsForm(true);
    const updateValues = this.state.vets.filter((vet) => vet.id === id)[0];
    this.setState({ updateValues: updateValues });
  };

  handleDeleteVet = (ids) => {
    deleteVet(ids).then(() => this.resetState());
  };

  handleLinks = () => {
    const newVet = this.state.vets.map((vet) => {
      vet.link = "/visits?vetId=" + vet.id;
      return vet;
    });
    this.setState({ vets: newVet });
  };

  render() {
    return (
      <React.Fragment>
        <VetTable
          vets={this.state.vets}
          setOpen={this.handleVetsForm}
          onDelete={this.handleDeleteVet}
          onUpdateForm={this.handleUpdateForm}
        />
        <VetForm
          onAdd={this.handleAddVet}
          open={this.state.vetsForm}
          setOpen={this.handleVetsForm}
          onUpdate={this.handleUpdateVet}
          updateValues={this.state.updateValues}
        />
      </React.Fragment>
    );
  }
}

export default VetPage;
