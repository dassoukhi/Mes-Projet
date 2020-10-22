using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace AuthentificationUser.Migrations
{
    public partial class addIdentity : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_AspNetUserLogins_ApplicationUser_UserId",
                table: "AspNetUserLogins");

            migrationBuilder.DropForeignKey(
                name: "FK_AspNetUserRoles_ApplicationUser_UserId",
                table: "AspNetUserRoles");

            migrationBuilder.DropForeignKey(
                name: "FK_AspNetUserTokens_ApplicationUser_UserId",
                table: "AspNetUserTokens");

            migrationBuilder.DropForeignKey(
                name: "FK_IdentityRoleClaim<string>_AspNetRoles_RoleId",
                table: "IdentityRoleClaim<string>");

            migrationBuilder.DropForeignKey(
                name: "FK_IdentityUserClaim<string>_ApplicationUser_UserId",
                table: "IdentityUserClaim<string>");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_ApplicationUser_TempId",
                table: "ApplicationUser");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_ApplicationUser_TempId2",
                table: "ApplicationUser");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_ApplicationUser_TempId3",
                table: "ApplicationUser");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_ApplicationUser_TempId4",
                table: "ApplicationUser");

            migrationBuilder.DropColumn(
                name: "TempId2",
                table: "ApplicationUser");

            migrationBuilder.DropColumn(
                name: "TempId3",
                table: "ApplicationUser");

            migrationBuilder.RenameTable(
                name: "IdentityUserClaim<string>",
                newName: "AspNetUserClaims");

            migrationBuilder.RenameTable(
                name: "IdentityRoleClaim<string>",
                newName: "AspNetRoleClaims");

            migrationBuilder.RenameTable(
                name: "ApplicationUser",
                newName: "AspNetUsers");

            migrationBuilder.RenameColumn(
                name: "TempId4",
                table: "AspNetUsers",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "TempId",
                table: "AspNetUsers",
                newName: "AccessFailedCount");

            migrationBuilder.AlterColumn<string>(
                name: "UserId",
                table: "AspNetUserClaims",
                nullable: false,
                oldClrType: typeof(int),
                oldNullable: true);

            migrationBuilder.AddColumn<int>(
                name: "Id",
                table: "AspNetUserClaims",
                nullable: false,
                defaultValue: 0)
                .Annotation("SqlServer:Identity", "1, 1");

            migrationBuilder.AddColumn<string>(
                name: "ClaimType",
                table: "AspNetUserClaims",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "ClaimValue",
                table: "AspNetUserClaims",
                nullable: true);

            migrationBuilder.AlterColumn<string>(
                name: "RoleId",
                table: "AspNetRoleClaims",
                nullable: false,
                oldClrType: typeof(string),
                oldNullable: true);

            migrationBuilder.AddColumn<int>(
                name: "Id",
                table: "AspNetRoleClaims",
                nullable: false,
                defaultValue: 0)
                .Annotation("SqlServer:Identity", "1, 1");

            migrationBuilder.AddColumn<string>(
                name: "ClaimType",
                table: "AspNetRoleClaims",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "ClaimValue",
                table: "AspNetRoleClaims",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "ConcurrencyStamp",
                table: "AspNetUsers",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Email",
                table: "AspNetUsers",
                maxLength: 256,
                nullable: true);

            migrationBuilder.AddColumn<bool>(
                name: "EmailConfirmed",
                table: "AspNetUsers",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<bool>(
                name: "LockoutEnabled",
                table: "AspNetUsers",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<DateTimeOffset>(
                name: "LockoutEnd",
                table: "AspNetUsers",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "NormalizedEmail",
                table: "AspNetUsers",
                maxLength: 256,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "NormalizedUserName",
                table: "AspNetUsers",
                maxLength: 256,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "PasswordHash",
                table: "AspNetUsers",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "PhoneNumber",
                table: "AspNetUsers",
                nullable: true);

            migrationBuilder.AddColumn<bool>(
                name: "PhoneNumberConfirmed",
                table: "AspNetUsers",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<string>(
                name: "SecurityStamp",
                table: "AspNetUsers",
                nullable: true);

            migrationBuilder.AddColumn<bool>(
                name: "TwoFactorEnabled",
                table: "AspNetUsers",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<string>(
                name: "UserName",
                table: "AspNetUsers",
                maxLength: 256,
                nullable: true);

            migrationBuilder.AddPrimaryKey(
                name: "PK_AspNetUserClaims",
                table: "AspNetUserClaims",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_AspNetRoleClaims",
                table: "AspNetRoleClaims",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_AspNetUsers",
                table: "AspNetUsers",
                column: "Id");

            migrationBuilder.CreateIndex(
                name: "IX_AspNetUserClaims_UserId",
                table: "AspNetUserClaims",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_AspNetRoleClaims_RoleId",
                table: "AspNetRoleClaims",
                column: "RoleId");

            migrationBuilder.CreateIndex(
                name: "EmailIndex",
                table: "AspNetUsers",
                column: "NormalizedEmail");

            migrationBuilder.CreateIndex(
                name: "UserNameIndex",
                table: "AspNetUsers",
                column: "NormalizedUserName",
                unique: true,
                filter: "[NormalizedUserName] IS NOT NULL");

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetRoleClaims_AspNetRoles_RoleId",
                table: "AspNetRoleClaims",
                column: "RoleId",
                principalTable: "AspNetRoles",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetUserClaims_AspNetUsers_UserId",
                table: "AspNetUserClaims",
                column: "UserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetUserLogins_AspNetUsers_UserId",
                table: "AspNetUserLogins",
                column: "UserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetUserRoles_AspNetUsers_UserId",
                table: "AspNetUserRoles",
                column: "UserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetUserTokens_AspNetUsers_UserId",
                table: "AspNetUserTokens",
                column: "UserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_AspNetRoleClaims_AspNetRoles_RoleId",
                table: "AspNetRoleClaims");

            migrationBuilder.DropForeignKey(
                name: "FK_AspNetUserClaims_AspNetUsers_UserId",
                table: "AspNetUserClaims");

            migrationBuilder.DropForeignKey(
                name: "FK_AspNetUserLogins_AspNetUsers_UserId",
                table: "AspNetUserLogins");

            migrationBuilder.DropForeignKey(
                name: "FK_AspNetUserRoles_AspNetUsers_UserId",
                table: "AspNetUserRoles");

            migrationBuilder.DropForeignKey(
                name: "FK_AspNetUserTokens_AspNetUsers_UserId",
                table: "AspNetUserTokens");

            migrationBuilder.DropPrimaryKey(
                name: "PK_AspNetUsers",
                table: "AspNetUsers");

            migrationBuilder.DropIndex(
                name: "EmailIndex",
                table: "AspNetUsers");

            migrationBuilder.DropIndex(
                name: "UserNameIndex",
                table: "AspNetUsers");

            migrationBuilder.DropPrimaryKey(
                name: "PK_AspNetUserClaims",
                table: "AspNetUserClaims");

            migrationBuilder.DropIndex(
                name: "IX_AspNetUserClaims_UserId",
                table: "AspNetUserClaims");

            migrationBuilder.DropPrimaryKey(
                name: "PK_AspNetRoleClaims",
                table: "AspNetRoleClaims");

            migrationBuilder.DropIndex(
                name: "IX_AspNetRoleClaims_RoleId",
                table: "AspNetRoleClaims");

            migrationBuilder.DropColumn(
                name: "ConcurrencyStamp",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "Email",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "EmailConfirmed",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "LockoutEnabled",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "LockoutEnd",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "NormalizedEmail",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "NormalizedUserName",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "PasswordHash",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "PhoneNumber",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "PhoneNumberConfirmed",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "SecurityStamp",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "TwoFactorEnabled",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "UserName",
                table: "AspNetUsers");

            migrationBuilder.DropColumn(
                name: "Id",
                table: "AspNetUserClaims");

            migrationBuilder.DropColumn(
                name: "ClaimType",
                table: "AspNetUserClaims");

            migrationBuilder.DropColumn(
                name: "ClaimValue",
                table: "AspNetUserClaims");

            migrationBuilder.DropColumn(
                name: "Id",
                table: "AspNetRoleClaims");

            migrationBuilder.DropColumn(
                name: "ClaimType",
                table: "AspNetRoleClaims");

            migrationBuilder.DropColumn(
                name: "ClaimValue",
                table: "AspNetRoleClaims");

            migrationBuilder.RenameTable(
                name: "AspNetUsers",
                newName: "ApplicationUser");

            migrationBuilder.RenameTable(
                name: "AspNetUserClaims",
                newName: "IdentityUserClaim<string>");

            migrationBuilder.RenameTable(
                name: "AspNetRoleClaims",
                newName: "IdentityRoleClaim<string>");

            migrationBuilder.RenameColumn(
                name: "AccessFailedCount",
                table: "ApplicationUser",
                newName: "TempId");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "ApplicationUser",
                newName: "TempId4");

            migrationBuilder.AddColumn<string>(
                name: "TempId2",
                table: "ApplicationUser",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddColumn<string>(
                name: "TempId3",
                table: "ApplicationUser",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AlterColumn<int>(
                name: "UserId",
                table: "IdentityUserClaim<string>",
                nullable: true,
                oldClrType: typeof(string));

            migrationBuilder.AlterColumn<string>(
                name: "RoleId",
                table: "IdentityRoleClaim<string>",
                nullable: true,
                oldClrType: typeof(string));

            migrationBuilder.AddUniqueConstraint(
                name: "AK_ApplicationUser_TempId",
                table: "ApplicationUser",
                column: "TempId");

            migrationBuilder.AddUniqueConstraint(
                name: "AK_ApplicationUser_TempId2",
                table: "ApplicationUser",
                column: "TempId2");

            migrationBuilder.AddUniqueConstraint(
                name: "AK_ApplicationUser_TempId3",
                table: "ApplicationUser",
                column: "TempId3");

            migrationBuilder.AddUniqueConstraint(
                name: "AK_ApplicationUser_TempId4",
                table: "ApplicationUser",
                column: "TempId4");

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetUserLogins_ApplicationUser_UserId",
                table: "AspNetUserLogins",
                column: "UserId",
                principalTable: "ApplicationUser",
                principalColumn: "TempId2",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetUserRoles_ApplicationUser_UserId",
                table: "AspNetUserRoles",
                column: "UserId",
                principalTable: "ApplicationUser",
                principalColumn: "TempId3",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_AspNetUserTokens_ApplicationUser_UserId",
                table: "AspNetUserTokens",
                column: "UserId",
                principalTable: "ApplicationUser",
                principalColumn: "TempId4",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_IdentityRoleClaim<string>_AspNetRoles_RoleId",
                table: "IdentityRoleClaim<string>",
                column: "RoleId",
                principalTable: "AspNetRoles",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_IdentityUserClaim<string>_ApplicationUser_UserId",
                table: "IdentityUserClaim<string>",
                column: "UserId",
                principalTable: "ApplicationUser",
                principalColumn: "TempId",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
